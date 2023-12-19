package edu.ap.mobiledevelopmentproject

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.OnProgressListener
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import java.io.IOException
import java.util.UUID

class EditProfileActivity : AppCompatActivity() {
    var selectedOption:String = "Male"
    var firstname: String = ""
    var lastname: String = ""
    var dob: String = ""
    var gender: String = ""
    var profilelink: String = ""
    var email: String = ""
    var password: String = ""
    private val PICK_IMAGE_REQUEST = 22
    private var filePath: Uri? = null
    var storageReference: StorageReference? = null
    // Declare ImageView as a public property
    lateinit var imageview: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val back:ImageView? = findViewById(R.id.back)
        back!!.setOnClickListener {
            finish()
        }
        firstname = intent.getStringExtra("firstname")!!
        lastname = intent.getStringExtra("lastname")!!
        gender = intent.getStringExtra("gender")!!
        email = intent.getStringExtra("email")!!
        password = intent.getStringExtra("password")!!
        dob = intent.getStringExtra("dob")!!
        profilelink = intent.getStringExtra("profilelink")!!

        val edtfirstname = findViewById<TextInputEditText>(R.id.firstname)
        val edtlastname = findViewById<TextInputEditText>(R.id.lastname)
        val edtdob = findViewById<TextInputEditText>(R.id.dob)
        val radioGroup: RadioGroup = findViewById(R.id.radiogroup)
        val radiomale: RadioButton = findViewById(R.id.male)
        val radiofemale: RadioButton = findViewById(R.id.female)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            val radioButton: RadioButton = findViewById(checkedId)
            selectedOption = radioButton.text.toString()

            // Now you can use the selectedOption as needed
            // For example, you might display it, perform an action, etc.
            println("Selected option: $selectedOption")
        }
        imageview= findViewById<ImageView>(R.id.imageview)


        edtfirstname.setText(firstname)
        edtlastname.setText(lastname)
        edtdob.setText(dob)

        if(gender.equals("Male")) {
            radiomale.isChecked = true
            selectedOption = "Male"
        }
        if(gender.equals("Female")) {
            radiofemale.isChecked = true
            selectedOption = "Female"
        }



        if(profilelink.equals("")){


        }
        else{
            Glide
                .with(this@EditProfileActivity)
                .load(profilelink)
                .centerCrop()
                .placeholder(R.drawable.ic_profile_icon_24)
                .into(imageview);

        }


        val save = findViewById<Button>(R.id.save)
        save.setOnClickListener({

            if(profilelink.equals("") && filePath==null){
                val firstname :String = edtfirstname.text.toString()
                val lastname :String = edtlastname.text.toString()
                val dob :String = edtdob.text.toString()
                val user:User = User(email,password,firstname,lastname,dob,selectedOption,"")
                FirebaseDatabase.getInstance().getReference().child("User").child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                Toast.makeText(this@EditProfileActivity,"Profile Updated",Toast.LENGTH_SHORT).show()

            } else {
                if (filePath != null) {
                    firstname = edtfirstname.text.toString()
                    lastname = edtlastname.text.toString()
                    dob = edtdob.text.toString()
                    uploadImage()
                }
                else {
                    Toast.makeText(this@EditProfileActivity, "Choose Image", Toast.LENGTH_SHORT)
                        .show()
                }
            }
                 })

        imageview.setOnClickListener({
            // Defining Implicit Intent to mobile gallery

            // Defining Implicit Intent to mobile gallery
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select Image from here..."
                ),
                PICK_IMAGE_REQUEST
            )
        })
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        // checking request code and result code
        // if request code is PICK_IMAGE_REQUEST and
        // resultCode is RESULT_OK
        // then set image in the image view
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {

            // Get the Uri of data
            filePath = data.data
            try {

                // Setting image on image view using Bitmap
                val bitmap = MediaStore.Images.Media
                    .getBitmap(
                        contentResolver,
                        filePath
                    )
                imageview.setImageBitmap(bitmap)
            } catch (e: IOException) {
                // Log the exception
                e.printStackTrace()
            }
        }
    }
    private fun uploadImage() {
        if (filePath != null) {

            // Code for showing progressDialog while uploading
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()
            storageReference = FirebaseStorage.getInstance().getReference()

            // Defining the child of storageReference
            val ref: StorageReference = storageReference!!
                .child(
                    "profileImage/"
                            + UUID.randomUUID().toString()
                )

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath!!)
                .addOnSuccessListener(
                    OnSuccessListener<Any?> { // Image uploaded successfully
                        // Dismiss dialog
                        progressDialog.dismiss()
                        ref.getDownloadUrl().addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                           profilelink = uri.toString()
                            val user:User = User(email,password,firstname,lastname,dob,selectedOption,profilelink)
                            FirebaseDatabase.getInstance().reference.child("User")
                                .child(FirebaseAuth.getInstance().currentUser!!.uid).setValue(user)
                            Toast.makeText(
                                this@EditProfileActivity,
                                "Profile Updated",
                                Toast.LENGTH_SHORT
                            ).show()

                        })
                    })
                .addOnFailureListener(OnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            this@EditProfileActivity,
                            "Failed " + e.message,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                })
                .addOnProgressListener(
                    object : OnProgressListener<UploadTask.TaskSnapshot?> {
                        // Progress Listener for loading
                        // percentage on the dialog box
                        override fun onProgress(
                            taskSnapshot: UploadTask.TaskSnapshot
                        ) {
                            val progress: Double = ((100.0
                                    * taskSnapshot.getBytesTransferred()
                                    / taskSnapshot.getTotalByteCount()))
                            progressDialog.setMessage(
                                ("Uploaded "
                                        + (progress.toInt()) + "%")
                            )
                        }
                    })
        }
    }
}
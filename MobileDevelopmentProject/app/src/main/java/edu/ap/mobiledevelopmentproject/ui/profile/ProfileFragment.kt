package edu.ap.mobiledevelopmentproject.ui.profile

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.ap.mobiledevelopmentproject.EditProfileActivity
import edu.ap.mobiledevelopmentproject.Login
import edu.ap.mobiledevelopmentproject.R

class ProfileFragment : Fragment() {
    var firstname: String = ""
    var lastname: String = ""
    var dob: String = ""
    var gender: String = ""
    var profilelink: String = ""
    var email: String = ""
    var password: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

//        val imageView = view.findViewById<ImageView>(R.id.imageView)
//        val base64Image = "yourBase64EncodedImage"
//        val bitmap = decodeBase64ToBitmap(base64Image)
//        imageView.setImageBitmap(bitmap)

        val edtprofile = view.findViewById<Button>(R.id.edtprofile)
        // Assuming you have a button with the ID "btnOpenSecondActivity"
        edtprofile.setOnClickListener {
            val intent = Intent(requireActivity(), EditProfileActivity::class.java)
            intent.putExtra("firstname",firstname)
            intent.putExtra("lastname",lastname)
            intent.putExtra("dob",dob)
            intent.putExtra("gender",gender)
            intent.putExtra("profilelink",profilelink)
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            startActivity(intent)
        }

        val logout = view.findViewById<Button>(R.id.logout)
        logout.setOnClickListener({
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(requireActivity(),Login::class.java))
            requireActivity().finish()

        })


        val tvfirstname = view.findViewById<TextView>(R.id.firstname)
        val tvlastname = view.findViewById<TextView>(R.id.lastname)
        val tvdob = view.findViewById<TextView>(R.id.dob)
        val tvgender = view.findViewById<TextView>(R.id.gender)
        val tvemail = view.findViewById<TextView>(R.id.email)
        val tvimageview = view.findViewById<ImageView>(R.id.imageview)

        if(FirebaseAuth.getInstance().currentUser!=null){
            FirebaseDatabase.getInstance().getReference().child("User")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        firstname =
                            snapshot.child("firstname").getValue(String::class.java)!!!!;
                        lastname =
                            snapshot.child("lastname").getValue(String::class.java)!!!!;
                        dob =
                            snapshot.child("dob").getValue(String::class.java)!!!!;
                        gender =
                            snapshot.child("gender").getValue(String::class.java)!!!!;
                        email =
                            snapshot.child("email").getValue(String::class.java)!!!!;
                        profilelink =
                            snapshot.child("profilelink").getValue(String::class.java)!!!!;
                        password =
                            snapshot.child("password").getValue(String::class.java)!!!!;


                        tvfirstname.setText(firstname)
                        tvlastname.setText(lastname)
                        tvdob.setText(dob)
                        tvgender.setText(gender)
                        tvemail.setText(email)
                        Glide
                            .with(requireActivity())
                            .load(profilelink)
                            .centerCrop()
                            .placeholder(R.drawable.ic_profile_icon_24)
                            .into(tvimageview);
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

        }



        return view
    }

    fun decodeBase64ToBitmap(encodedImage: String): Bitmap? {
        val decodedBytes = Base64.decode(encodedImage, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
    }

}
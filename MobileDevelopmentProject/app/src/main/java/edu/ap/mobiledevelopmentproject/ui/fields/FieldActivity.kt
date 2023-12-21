package edu.ap.mobiledevelopmentproject.ui.fields

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.ap.mobiledevelopmentproject.R
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class FieldActivity : AppCompatActivity() {
    var hallid: String = ""
    var name: String = ""
    var image: String = ""
    var location: String = ""
    var date: String = ""
    lateinit var imageview: ImageView
    lateinit var tvname: TextView
    lateinit var tvlocation: TextView

    private lateinit var card900: MaterialCardView
    private lateinit var card1030: MaterialCardView
    private lateinit var card1200: MaterialCardView
    private lateinit var card1330: MaterialCardView
    private lateinit var card1500: MaterialCardView
    private lateinit var card1630: MaterialCardView
    private lateinit var card1800: MaterialCardView
    private lateinit var card1930: MaterialCardView
    private lateinit var card2100: MaterialCardView
    var iscard900 = false
    var iscard1030 = false
    var iscard1200 = false
    var iscard1330 = false
    var iscard1500 = false
    var iscard1630 = false
    var iscard1800 = false
    var iscard1930 = false
    var iscard2100 = false

    var simpleDatePicker: DatePicker? = null


    private lateinit var view900: View
    private lateinit var view1030: View
    private lateinit var view1200: View
    private lateinit var view1330: View
    private lateinit var view1500: View
    private lateinit var view1630: View
    private lateinit var view1800: View
    private lateinit var view1930: View
    private lateinit var view2100: View


    val list = mutableListOf<String>() // for field
    val listtime = mutableListOf<String>() // for time

    var id: String? = ""


    //for image
    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView

    //for user name

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView

    var tt: String? = ""
    var subid: String? = ""
    var isalready1: Boolean = false
    var isalready2: Boolean = false
    var isalready3: Boolean = false
    var isalready4: Boolean = false

    var isalreadyuser: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        id = intent.getStringExtra("id");

        simpleDatePicker = findViewById(R.id.simpleDatePicker);
        val calendar: Calendar = Calendar.getInstance()
        simpleDatePicker!!.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONDAY),
            calendar.get(Calendar.DAY_OF_MONTH),
            null
        )


        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        date = dateFormat.format(currentDate).toString()
        // Set the OnDateChangedListener
        simpleDatePicker!!.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            // Handle date changes here
            val month = monthOfYear + 1
            date = dayOfMonth.toString() + "/" + month + "/" + year
            val whiteColor =
                ContextCompat.getColor(this@FieldActivity, R.color.white)
            val newColor =
                ContextCompat.getColor(this@FieldActivity, R.color.gray)
            card900.setCardBackgroundColor(whiteColor)
            card1030.setCardBackgroundColor(whiteColor)
            card1200.setCardBackgroundColor(whiteColor)
            card1330.setCardBackgroundColor(whiteColor)
            card1500.setCardBackgroundColor(whiteColor)
            card1630.setCardBackgroundColor(whiteColor)
            card1800.setCardBackgroundColor(whiteColor)
            card1930.setCardBackgroundColor(whiteColor)
            card2100.setCardBackgroundColor(whiteColor)
            tt = ""
            if (tt!!.isEmpty()) {
                tt = "09:00"
                if(isalready1 && isalready2&& isalready3 && isalready4){

                }
                else{

                card900.setCardBackgroundColor(newColor)
                }
            }
            viewdata()


        }
        card900 = findViewById(R.id.card900)
        card1030 = findViewById(R.id.card1030)
        card1200 = findViewById(R.id.card1200)
        card1330 = findViewById(R.id.card1330)
        card1500 = findViewById(R.id.card1500)
        card1630 = findViewById(R.id.card1630)
        card1800 = findViewById(R.id.card1800)
        card1930 = findViewById(R.id.card1930)
        card2100 = findViewById(R.id.card2100)


        view900 = findViewById(R.id.view0900)
        view1030 = findViewById(R.id.view1030)
        view1200 = findViewById(R.id.view1200)
        view1330 = findViewById(R.id.view1330)
        view1500 = findViewById(R.id.view1500)
        view1630 = findViewById(R.id.view1630)
        view1800 = findViewById(R.id.view1800)
        view1930 = findViewById(R.id.view1930)
        view2100 = findViewById(R.id.view2100)



        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)

        // You can set any color you want here
        val newColor = ContextCompat.getColor(this, R.color.gray)
        val oldColor = ContextCompat.getColor(this, R.color.white)
        card900.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){

            }
            else{

                card900.setCardBackgroundColor(newColor)
            }
            tt = "09:00"
            iscard900 = true
            iscard1030 = false
            iscard1200 = false
            iscard1330 = false
            iscard1500 = false
            iscard1630 = false
            iscard1800 = false
            iscard1930 = false
            iscard2100 = false
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)



            viewdata()


        })

        card1030.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){
                val redcolor =
                    ContextCompat.getColor(this@FieldActivity, R.color.red)
                card1030.setCardBackgroundColor(newColor)
            }
            else  if(isalready1 || isalready2||  isalready3|| isalready4){
                val blueColor =
                    ContextCompat.getColor(this@FieldActivity, R.color.blue)
                card1030.setCardBackgroundColor(newColor)
            }
            else{

                card1030.setCardBackgroundColor(newColor)
            }

            tt = "10:30"
            iscard1030 = true
            iscard900 = false
            iscard1200 = false
            iscard1330 = false
            iscard1500 = false
            iscard1630 = false
            iscard1800 = false
            iscard1930 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)
            viewdata()
        })
        card1200.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){
            }
            else{

                card1200.setCardBackgroundColor(newColor)
            }

            tt = "12:00"
            iscard1200 = true
            iscard900 = false
            iscard1030 = false
            iscard1330 = false
            iscard1500 = false
            iscard1630 = false
            iscard1800 = false
            iscard1930 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)


            viewdata()
        })

        card1330.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){
            }
            else{

                card1330.setCardBackgroundColor(newColor)
            }

            tt = "13:30"
            iscard1330 = true
            iscard900 = false
            iscard1030 = false
            iscard1200 = false
            iscard1500 = false
            iscard1630 = false
            iscard1800 = false
            iscard1930 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)


            viewdata()
        })


        card1500.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){

            }
            else{

                card1500.setCardBackgroundColor(newColor)
            }
            tt = "15:00"
            iscard1500 = true
            iscard900 = false
            iscard1030 = false
            iscard1200 = false
            iscard1330 = false
            iscard1630 = false
            iscard1800 = false
            iscard1930 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)

            viewdata()
        })
        card1630.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){
            }
            else{

                card1630.setCardBackgroundColor(newColor)
            }
            tt = "16:30"
            iscard1630 = true
            iscard900 = false
            iscard1030 = false
            iscard1200 = false
            iscard1330 = false
            iscard1500 = false
            iscard1800 = false
            iscard1930 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)


            viewdata()
        })
        card1800.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){
            }
            else{

                card1800.setCardBackgroundColor(newColor)
            }

            tt = "18:00"
            iscard1800 = true
            iscard900 = false
            iscard1030 = false
            iscard1200 = false
            iscard1330 = false
            iscard1500 = false
            iscard1630 = false
            iscard1930 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)


            viewdata()
        })


        card1930.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){

            }
            else{

                card1930.setCardBackgroundColor(newColor)
            }

            tt = "19:30"
            iscard1930 = true
            iscard900 = false
            iscard1030 = false
            iscard1200 = false
            iscard1330 = false
            iscard1500 = false
            iscard1630 = false
            iscard1800 = false
            iscard2100 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card2100.setCardBackgroundColor(oldColor)


            viewdata()
        })
        card2100.setOnClickListener({
            // Change the background color of the card
            if(isalready1 && isalready2&& isalready3&& isalready4){
            }
            else{

                card2100.setCardBackgroundColor(newColor)
            }

            tt = "21:00"
            iscard2100 = true
            iscard900 = false
            iscard1030 = false
            iscard1200 = false
            iscard1330 = false
            iscard1500 = false
            iscard1630 = false
            iscard1800 = false
            iscard1930 = false
            card900.setCardBackgroundColor(oldColor)
            card1030.setCardBackgroundColor(oldColor)
            card1200.setCardBackgroundColor(oldColor)
            card1330.setCardBackgroundColor(oldColor)
            card1500.setCardBackgroundColor(oldColor)
            card1630.setCardBackgroundColor(oldColor)
            card1800.setCardBackgroundColor(oldColor)
            card1930.setCardBackgroundColor(oldColor)

            viewdata()
        })


        imageview = findViewById(R.id.imageview)
        tvname = findViewById(R.id.tv)
        tvlocation = findViewById(R.id.location)
        FirebaseDatabase.getInstance().getReference().child("Halls").child(id.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(ds: DataSnapshot) {

                    hallid = ds.key.toString()
                    name = ds.child("name").getValue().toString()
                    image = ds.child("image").getValue().toString()
                    location = ds.child("location").getValue().toString()

                    tvname.text = name
                    tvlocation.text = location
                    Glide
                        .with(this@FieldActivity)
                        .load(image)
                        .centerCrop()
                        .placeholder(R.drawable.ic_profile_icon_24)
                        .into(imageview);


                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

        val back: ImageView? = findViewById(R.id.back)
        back!!.setOnClickListener {
            finish()
        }

        viewdata()

        img1.setOnClickListener({
            if(subid!!.isEmpty()){

                    var time: String? = ""
                    if (iscard900) time = "09:00"
                    if (iscard1030) time = "10:30"
                    if (iscard1200) time = "12:00"
                    if (iscard1330) time = "13:30"
                    if (iscard1500) time = "15:00"
                    if (iscard1630) time = "16:30"
                    if (iscard1800) time = "18:00"
                    if (iscard1930) time = "19:30"
                    if (iscard2100) time = "21:00"

                    // Create user details map
                    val userMap: HashMap<String, Any> = HashMap()
                    userMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
                    userMap["userEmail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
// Add other user details as needed

// Create team map
                    val teamMap: HashMap<String, Any> = HashMap()

// Add user details to the team
                    teamMap[FirebaseAuth.getInstance().currentUser!!.uid.toString()] = userMap

                    val map: HashMap<String, Any> = HashMap()
                    map["date"] = date
                    map["time"] = time.toString()
                    map["id"] = hallid
                    map["name"] = name
                    map["location"] = location
                    map["userid"] = FirebaseAuth.getInstance().currentUser!!.uid
                    map["useremail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
                    map["imageUrl"] = image
                    map["field"] = "field1"
                    map["team1"] = teamMap // Add user details to the booking

                    FirebaseDatabase.getInstance().getReference().child("Booking").push().setValue(map)
                }
            else if (!isalready1 && !isalreadyuser) {
                // Create user details map
                val userMap: HashMap<String, Any> = HashMap()
                userMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
                userMap["userEmail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
// Add other user details as needed

// Create team map
                val teamMap: HashMap<String, Any> = HashMap()

// Add user details to the team
                teamMap[FirebaseAuth.getInstance().currentUser!!.uid.toString()] = userMap
                FirebaseDatabase.getInstance().getReference().child("Booking")
                    .child(subid.toString()).child("team1").updateChildren(teamMap)
            }
        })

        img2.setOnClickListener({
            if (!isalready2 && !isalreadyuser) {
                // Create user details map
                val userMap: HashMap<String, Any> = HashMap()
                userMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
                userMap["userEmail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
// Add other user details as needed

// Create team map
                val teamMap: HashMap<String, Any> = HashMap()

// Add user details to the team
                teamMap[FirebaseAuth.getInstance().currentUser!!.uid.toString()] = userMap
                FirebaseDatabase.getInstance().getReference().child("Booking")
                    .child(subid.toString()).child("team1").updateChildren(teamMap)

            }

        })


        img3.setOnClickListener({
            if (!isalready3 && !isalreadyuser) {
                // Create user details map
                val userMap: HashMap<String, Any> = HashMap()
                userMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
                userMap["userEmail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
// Add other user details as needed

// Create team map
                val teamMap: HashMap<String, Any> = HashMap()

// Add user details to the team
                teamMap[FirebaseAuth.getInstance().currentUser!!.uid.toString()] = userMap
                FirebaseDatabase.getInstance().getReference().child("Booking")
                    .child(subid.toString()).child("team2").updateChildren(teamMap)

            }

        })

        img4.setOnClickListener({

            if (!isalready4 && !isalreadyuser) {
                // Create user details map
                val userMap: HashMap<String, Any> = HashMap()
                userMap["userId"] = FirebaseAuth.getInstance().currentUser!!.uid
                userMap["userEmail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
// Add other user details as needed

// Create team map
                val teamMap: HashMap<String, Any> = HashMap()

// Add user details to the team
                teamMap[FirebaseAuth.getInstance().currentUser!!.uid.toString()] = userMap
                FirebaseDatabase.getInstance().getReference().child("Booking")
                    .child(subid.toString()).child("team2").updateChildren(teamMap)

            }

        })


        val whiteColor =
            ContextCompat.getColor(this@FieldActivity, R.color.white)
        card900.setCardBackgroundColor(whiteColor)
        card1030.setCardBackgroundColor(whiteColor)
        card1200.setCardBackgroundColor(whiteColor)
        card1330.setCardBackgroundColor(whiteColor)
        card1500.setCardBackgroundColor(whiteColor)
        card1630.setCardBackgroundColor(whiteColor)
        card1800.setCardBackgroundColor(whiteColor)
        card1930.setCardBackgroundColor(whiteColor)
        card2100.setCardBackgroundColor(whiteColor)
        tt = ""
        if (tt!!.isEmpty()) {
            tt = "09:00"
            if(isalready1 && isalready2&& isalready3&& isalready4){
                val redcolor =
                    ContextCompat.getColor(this@FieldActivity, R.color.red)
                card900.setCardBackgroundColor(redcolor)
            }
            else  if(isalready1 || isalready2||  isalready3|| isalready4){
                val blueColor =
                    ContextCompat.getColor(this@FieldActivity, R.color.blue)
                card900.setCardBackgroundColor(blueColor)
            }
            else{

                card900.setCardBackgroundColor(newColor)
            }
        }

        viewdata()

    }

    private fun viewdata() {
        var counter: Int = 0;

        val bookedColor =
            ContextCompat.getColor(this@FieldActivity, androidx.appcompat.R.color.material_blue_grey_800)

        if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseDatabase.getInstance().getReference().child("Booking")
                .orderByChild("id").equalTo(id)
                .addValueEventListener(object : ValueEventListener {
                    @SuppressLint("SuspiciousIndentation")
                    override fun onDataChange(snapshot: DataSnapshot) {
                        isalready1 = false
                        isalready2 = false
                        isalready3 = false
                        isalready4 = false
                        isalreadyuser = false

                        list.clear()
                        listtime.clear()



                        tv1.setText("")
                        tv2.setText("")
                        tv3.setText("")
                        tv4.setText("")
                        img1.setImageResource(R.drawable.ic_add_icon_24)
                        img2.setImageResource(R.drawable.ic_add_icon_24)
                        img3.setImageResource(R.drawable.ic_add_icon_24)
                        img4.setImageResource(R.drawable.ic_add_icon_24)
                        subid = ""


                        for (ds: DataSnapshot in snapshot.children) {
                            val dd = ds.child("date").getValue(String::class.java)
                            val bookingid = ds.child("id").getValue(String::class.java)
                            val time = ds.child("time").getValue(String::class.java)
                            val field = ds.child("field").getValue(String::class.java)

                            if (date.equals(dd)) {
                                if (bookingid.equals(id)) {
                                    if (time.equals("09:00")) {

                                        if (counter == 0) {
                                            card900.setCardBackgroundColor(bookedColor)
                                        }

                                    }
                                    if (time.equals("10:30")) {

                                        if (counter == 0) {
                                            card1030.setCardBackgroundColor(bookedColor)
                                        }
                                    }
                                    if (time.equals("12:00")) {
                                        if (counter == 0) {
                                            card1200.setCardBackgroundColor(bookedColor)
                                        }
                                    }
                                    if (time.equals("13:30")) {
                                        if (counter == 0) {
                                            card1330.setCardBackgroundColor(bookedColor)
                                        }
                                    }
                                    if (time.equals("15:00")) {
                                        if (counter == 0) {
                                            card1500.setCardBackgroundColor(bookedColor)
                                        }
                                    }
                                    if (time.equals("16:30")) {

                                        if (counter == 0) {
                                            card1630.setCardBackgroundColor(bookedColor)
                                        }

                                    }
                                    if (time.equals("18:00")) {
                                        if (counter == 0) {
                                            card1800.setCardBackgroundColor(bookedColor)
                                        }

                                    }
                                    if (time.equals("19:30")) {
                                        if (counter == 0) {
                                            card1930.setCardBackgroundColor(bookedColor)
                                        }

                                    }
                                    if (time.equals("21:00")) {
                                        if (counter == 0) {
                                            card2100.setCardBackgroundColor(bookedColor)
                                        }
                                    }

                                }
                            }


                            if (date.equals(dd) && time.equals(tt)) {
                                subid = ds.key
                                //for team field
                                if (ds.hasChild("team1")) {
                                    var count: Int = ds.child("team1").childrenCount.toInt();
                                    if (count == 1) {

                                        val bookingData = ds.value as Map<*, *>

                                        // Check if "team1" exists in the booking data
                                        if (bookingData.containsKey("team1")) {
                                            val team1Data = bookingData["team1"] as Map<*, *>

                                            // Get the first user ID in team1
                                            val userKeys = team1Data.keys
                                            val firstUserId = userKeys.firstOrNull() as String?

                                            if (firstUserId != null) {
                                                isalready1 = true
                                                val firstUserDetails =
                                                    team1Data[firstUserId] as Map<*, *>
                                                val firstUserIdValue =
                                                    firstUserDetails["userId"] as String
                                                if (firstUserIdValue.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                                    isalreadyuser = true
                                                }
                                                Log.d(
                                                    "Team1UserId",
                                                    "User ID of Team 1: $firstUserIdValue"
                                                )

                                                FirebaseDatabase.getInstance().getReference()
                                                    .child("User").child(firstUserIdValue)
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            var firstname =
                                                                snapshot.child("firstname")
                                                                    .getValue(String::class.java)!!!!;
                                                            var profilelink =
                                                                snapshot.child("profilelink")
                                                                    .getValue(String::class.java)!!!!;
                                                            tv1.setText(firstname)
                                                            Glide
                                                                .with(this@FieldActivity)
                                                                .load(profilelink)
                                                                .centerCrop()
                                                                .placeholder(R.drawable.ic_profile_icon_24)
                                                                .into(img1);


                                                        }

                                                        override fun onCancelled(error: DatabaseError) {

                                                        }
                                                    })

                                            }
                                        }
                                    } else {
                                        val bookingData = ds.value as Map<*, *>

                                        // Check if "team1" exists in the booking data
                                        if (bookingData.containsKey("team1")) {
                                            val team1Data = bookingData["team1"] as Map<*, *>

                                            // Get the list of user IDs in team1
                                            val userKeys = team1Data.keys.toList()

                                            // Check if there are at least two users in team1
                                            if (userKeys.size >= 2) {
                                                // Get the second user ID
                                                val firstUserId = userKeys[0] as String
                                                val secondUserId = userKeys[1] as String

                                                val firstUserDetails =
                                                    team1Data[firstUserId] as Map<*, *>
                                                val firstUserIdValue =
                                                    firstUserDetails["userId"] as String
                                                isalready1 = true
                                                if (firstUserIdValue.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                                    isalreadyuser = true
                                                }

                                                val secondUserDetails =
                                                    team1Data[secondUserId] as Map<*, *>
                                                val secondUserIdValue =
                                                    secondUserDetails["userId"] as String
                                                isalready2 = true
                                                if (secondUserIdValue.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                                    isalreadyuser = true
                                                }
                                                FirebaseDatabase.getInstance().getReference()
                                                    .child("User").child(firstUserIdValue)
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            var firstname =
                                                                snapshot.child("firstname")
                                                                    .getValue(String::class.java)!!!!;
                                                            var profilelink =
                                                                snapshot.child("profilelink")
                                                                    .getValue(String::class.java)!!!!;
                                                            tv1.setText(firstname)
                                                            Glide
                                                                .with(this@FieldActivity)
                                                                .load(profilelink)
                                                                .centerCrop()
                                                                .placeholder(R.drawable.ic_profile_icon_24)
                                                                .into(img1);


                                                        }

                                                        override fun onCancelled(error: DatabaseError) {

                                                        }
                                                    })


                                                //for second user
                                                FirebaseDatabase.getInstance().getReference()
                                                    .child("User").child(secondUserIdValue)
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            var firstname =
                                                                snapshot.child("firstname")
                                                                    .getValue(String::class.java)!!!!;
                                                            var profilelink =
                                                                snapshot.child("profilelink")
                                                                    .getValue(String::class.java)!!!!;
                                                            tv2.setText(firstname)
                                                            Glide
                                                                .with(this@FieldActivity)
                                                                .load(profilelink)
                                                                .centerCrop()
                                                                .placeholder(R.drawable.ic_profile_icon_24)
                                                                .into(img2);


                                                        }

                                                        override fun onCancelled(error: DatabaseError) {

                                                        }
                                                    })


                                            }
                                        }


                                    }

                                }



                                if (ds.hasChild("team2")) {
                                    var count: Int = ds.child("team2").childrenCount.toInt();
                                    if (count == 1) {

                                        val bookingData = ds.value as Map<*, *>

                                        // Check if "team1" exists in the booking data
                                        if (bookingData.containsKey("team2")) {
                                            val team2Data = bookingData["team2"] as Map<*, *>

                                            // Get the first user ID in team2
                                            val userKeys = team2Data.keys
                                            val firstUserId = userKeys.firstOrNull() as String?

                                            if (firstUserId != null) {
                                                val firstUserDetails =
                                                    team2Data[firstUserId] as Map<*, *>
                                                val firstUserIdValue =
                                                    firstUserDetails["userId"] as String
                                                if (firstUserIdValue.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                                    isalreadyuser = true
                                                }
                                                Log.d(
                                                    "team2UserId",
                                                    "User ID of Team 1: $firstUserIdValue"
                                                )
                                                isalready3 = true
                                                FirebaseDatabase.getInstance().getReference()
                                                    .child("User").child(firstUserIdValue)
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            var firstname =
                                                                snapshot.child("firstname")
                                                                    .getValue(String::class.java)!!!!;
                                                            var profilelink =
                                                                snapshot.child("profilelink")
                                                                    .getValue(String::class.java)!!!!;
                                                            tv3.setText(firstname)
                                                            Glide
                                                                .with(this@FieldActivity)
                                                                .load(profilelink)
                                                                .centerCrop()
                                                                .placeholder(R.drawable.ic_profile_icon_24)
                                                                .into(img3);


                                                        }

                                                        override fun onCancelled(error: DatabaseError) {

                                                        }
                                                    })

                                            }
                                        }
                                    } else {
                                        val bookingData = ds.value as Map<*, *>

                                        // Check if "team2" exists in the booking data
                                        if (bookingData.containsKey("team2")) {
                                            val team2Data = bookingData["team2"] as Map<*, *>

                                            // Get the list of user IDs in team2
                                            val userKeys = team2Data.keys.toList()

                                            // Check if there are at least two users in team2
                                            if (userKeys.size >= 2) {
                                                // Get the second user ID
                                                val firstUserId = userKeys[0] as String
                                                val secondUserId = userKeys[1] as String

                                                val firstUserDetails =
                                                    team2Data[firstUserId] as Map<*, *>
                                                val firstUserIdValue =
                                                    firstUserDetails["userId"] as String

                                                isalready3 = true
                                                if (firstUserIdValue.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                                    isalreadyuser = true
                                                }
                                                val secondUserDetails =
                                                    team2Data[secondUserId] as Map<*, *>
                                                val secondUserIdValue =
                                                    secondUserDetails["userId"] as String
                                                isalready4 = true
                                                if (secondUserIdValue.equals(FirebaseAuth.getInstance().currentUser!!.uid)) {
                                                    isalreadyuser = true
                                                }
                                                FirebaseDatabase.getInstance().getReference()
                                                    .child("User").child(firstUserIdValue)
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            var firstname =
                                                                snapshot.child("firstname")
                                                                    .getValue(String::class.java)!!!!;
                                                            var profilelink =
                                                                snapshot.child("profilelink")
                                                                    .getValue(String::class.java)!!!!;
                                                            tv3.setText(firstname)
                                                            Glide
                                                                .with(this@FieldActivity)
                                                                .load(profilelink)
                                                                .centerCrop()
                                                                .placeholder(R.drawable.ic_profile_icon_24)
                                                                .into(img3);


                                                        }

                                                        override fun onCancelled(error: DatabaseError) {

                                                        }
                                                    })


                                                //for second user
                                                FirebaseDatabase.getInstance().getReference()
                                                    .child("User").child(secondUserIdValue)
                                                    .addValueEventListener(object :
                                                        ValueEventListener {
                                                        override fun onDataChange(snapshot: DataSnapshot) {
                                                            var firstname =
                                                                snapshot.child("firstname")
                                                                    .getValue(String::class.java)!!!!;
                                                            var profilelink =
                                                                snapshot.child("profilelink")
                                                                    .getValue(String::class.java)!!!!;
                                                            tv4.setText(firstname)
                                                            Glide
                                                                .with(this@FieldActivity)
                                                                .load(profilelink)
                                                                .centerCrop()
                                                                .placeholder(R.drawable.ic_profile_icon_24)
                                                                .into(img4);


                                                        }

                                                        override fun onCancelled(error: DatabaseError) {

                                                        }
                                                    })


                                            }
                                        }
                                    }

                                }
                                listtime.add(time.toString());
                                if (bookingid.equals(id)) {
                                    if (field.equals("field1")) {
                                        list.add("field1")
                                    }
                                    if (field.equals("field2")) {
                                        list.add("field2")
                                    }
                                    if (field.equals("field3")) {
                                        list.add("field3")
                                    }


                                }

                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }


}
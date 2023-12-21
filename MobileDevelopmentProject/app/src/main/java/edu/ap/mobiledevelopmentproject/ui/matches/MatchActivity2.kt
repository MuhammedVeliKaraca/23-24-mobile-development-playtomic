package edu.ap.mobiledevelopmentproject.ui.matches

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.ap.mobiledevelopmentproject.R
import edu.ap.mobiledevelopmentproject.SportsHall
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MatchActivity2 : AppCompatActivity() {
    var hallid: String = ""
    var name: String = ""
    var image: String = ""
    var location: String = ""
    var date: String = ""
    lateinit var imageview: ImageView
    lateinit var tvname: TextView
    lateinit var tvlocation: TextView
    private lateinit var expandButton: Button
    private lateinit var icon: ImageView

    private lateinit var expandButton2: Button
    private lateinit var icon2: ImageView

    private lateinit var expandButton3: Button
    private lateinit var icon3: ImageView


    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView

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
    var tt: String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match2)

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
                ContextCompat.getColor(this@MatchActivity2, R.color.white)
            val newColor =
                ContextCompat.getColor(this@MatchActivity2, R.color.gray)
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
                card900.setCardBackgroundColor(newColor)
            }
            viewdata()


        }

        icon = findViewById(R.id.icon)
        expandButton = findViewById(R.id.expandButton)
        icon2 = findViewById(R.id.icon2)
        expandButton2 = findViewById(R.id.expandButton2)
        icon3 = findViewById(R.id.icon3)
        expandButton3 = findViewById(R.id.expandButton3)
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

        tv1 = findViewById(R.id.textView2)
        tv2 = findViewById(R.id.textView4)
        tv3 = findViewById(R.id.textView6)
        // You can set any color you want here
        val newColor = ContextCompat.getColor(this, R.color.gray)
        val oldColor = ContextCompat.getColor(this, R.color.white)
        card900.setOnClickListener({
            // Change the background color of the card
            card900.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()

            viewdata()


        })

        card1030.setOnClickListener({
            // Change the background color of the card
            card1030.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })
        card1200.setOnClickListener({
            // Change the background color of the card
            card1200.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })

        card1330.setOnClickListener({
            // Change the background color of the card
            card1330.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })

        card1500.setOnClickListener({
            // Change the background color of the card
            card1500.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })
        card1630.setOnClickListener({
            // Change the background color of the card
            card1630.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })
        card1800.setOnClickListener({
            // Change the background color of the card
            card1800.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })


        card1930.setOnClickListener({
            // Change the background color of the card
            card1930.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })
        card2100.setOnClickListener({
            // Change the background color of the card
            card2100.setCardBackgroundColor(newColor)
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

            toggleButtonVisibility()
            toggleButtonVisibility2()
            toggleButtonVisibility3()
            viewdata()
        })


        expandButton.setOnClickListener({
            if (expandButton.text.equals("Booked")) {
                Toast.makeText(this@MatchActivity2, "Already Booked", Toast.LENGTH_LONG).show()
            } else {
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
                Toast.makeText(this@MatchActivity2, "Booking done", Toast.LENGTH_LONG).show()

            }
        })

        expandButton2.setOnClickListener({
            if (expandButton2.text.equals("Booked")) {
                Toast.makeText(this@MatchActivity2, "Already Booked", Toast.LENGTH_LONG).show()
            } else {
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
                map["field"] = "field2"
                map["team1"] = teamMap // Add user details to the booking


                FirebaseDatabase.getInstance().getReference().child("Booking").push().setValue(map)
                Toast.makeText(this@MatchActivity2, "Booking done", Toast.LENGTH_LONG).show()

            }
        })

        expandButton3.setOnClickListener({
            if (expandButton3.text.equals("Booked")) {
                Toast.makeText(this@MatchActivity2, "Already Booked", Toast.LENGTH_LONG).show()
            } else {
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
                map["field"] = "field3"
                map["team1"] = teamMap // Add user details to the booking

                FirebaseDatabase.getInstance().getReference().child("Booking").push().setValue(map)
                Toast.makeText(this@MatchActivity2, "Booking done", Toast.LENGTH_LONG).show()

            }
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
                        .with(this@MatchActivity2)
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

        icon.setOnClickListener {
            toggleButtonVisibility()
        }

        icon2.setOnClickListener {
            toggleButtonVisibility2()
        }
        icon3.setOnClickListener {
            toggleButtonVisibility3()
        }


        val whiteColor =
            ContextCompat.getColor(this@MatchActivity2, R.color.white)
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
            card900.setCardBackgroundColor(newColor)
        }

        viewdata()

    }


    private fun viewdata() {

        val bookedColor =
            ContextCompat.getColor(this@MatchActivity2, androidx.appcompat.R.color.material_blue_grey_800)



        list.clear()
        listtime.clear()
        view900.visibility = View.GONE
        view1030.visibility = View.GONE
        view1200.visibility = View.GONE
        view1330.visibility = View.GONE
        view1500.visibility = View.GONE
        view1630.visibility = View.GONE
        view1800.visibility = View.GONE
        view1930.visibility = View.GONE
        view2100.visibility = View.GONE



        tv1.setText("Available")
        tv2.setText("Available")
        tv3.setText("Available")

        var counter: Int = 0;

        if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseDatabase.getInstance().getReference().child("Booking")
                .orderByChild("id").equalTo(id)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds: DataSnapshot in snapshot.children) {
                            val dd = ds.child("date").getValue(String::class.java)
                            val bookingid = ds.child("id").getValue(String::class.java)
                            val time = ds.child("time").getValue(String::class.java)
                            val field = ds.child("field").getValue(String::class.java)

                            if (date.equals(dd) && time.equals(tt)) {
                                if (bookingid.equals(id)) {
                                    if (time.equals("09:00")) {

                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("10:30")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("12:00")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("13:30")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("15:00")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("16:30")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("18:00")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("19:30")) {
                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    } else if (time.equals("21:00")) {

                                        if (field.equals("field1")) {
                                            counter++
                                            expandButton.visibility = View.GONE
                                            list.add("field1")
                                            tv1.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field2")) {
                                            counter++
                                            expandButton2.visibility = View.GONE
                                            list.add("field2")
                                            tv2.setText("Booked")
                                            listtime.add(time.toString());
                                        } else if (field.equals("field3")) {
                                            counter++
                                            expandButton3.visibility = View.GONE
                                            list.add("field3")
                                            tv3.setText("Booked")
                                            listtime.add(time.toString());
                                        }
                                    }

                                }

                            }

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

                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }

    private fun toggleButtonVisibility() {
        tv1.setText("Available")
        if (expandButton.visibility == View.VISIBLE) {
            icon.setImageResource(R.drawable.baseline_expand_more_24)
            expandButton.visibility = View.GONE

        } else {
            expandButton.visibility = View.VISIBLE
            icon.setImageResource(R.drawable.baseline_expand_less_24)
            if (iscard900) {
                if (listtime.contains("09:00")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1030) {
                if (listtime.contains("10:30")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1200) {
                if (listtime.contains("12:00")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1330) {
                if (listtime.contains("13:30")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1500) {
                if (listtime.contains("15:00")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1630) {
                if (listtime.contains("16:30")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1800) {
                if (listtime.contains("18:00")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard1930) {
                if (listtime.contains("19:30")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }
            if (iscard2100) {
                if (listtime.contains("21:00")) {
                    if (list.contains("field1")) {
                        expandButton.visibility = View.GONE
                        tv1.setText("Booked")

                    }
                }
            }


        }
    }

    private fun toggleButtonVisibility2() {
        tv2.setText("Available")
        if (expandButton2.visibility == View.VISIBLE) {
            expandButton2.visibility = View.GONE
            icon2.setImageResource(R.drawable.baseline_expand_more_24)

        } else {
            expandButton2.visibility = View.VISIBLE
            icon2.setImageResource(R.drawable.baseline_expand_less_24)

            if (iscard900) {
                if (listtime.contains("09:00")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1030) {
                if (listtime.contains("10:30")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1200) {
                if (listtime.contains("12:00")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1330) {
                if (listtime.contains("13:30")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1500) {
                if (listtime.contains("15:00")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1630) {
                if (listtime.contains("16:30")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1800) {
                if (listtime.contains("18:00")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard1930) {
                if (listtime.contains("19:30")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }
            if (iscard2100) {
                if (listtime.contains("21:00")) {
                    if (list.contains("field2")) {
                        expandButton2.visibility = View.GONE
                        tv2.setText("Booked")
                    }
                }
            }


        }
    }

    private fun toggleButtonVisibility3() {
        tv3.setText("Available")
        if (expandButton3.visibility == View.VISIBLE) {
            expandButton3.visibility = View.GONE
            icon3.setImageResource(R.drawable.baseline_expand_more_24)

        } else {
            expandButton3.visibility = View.VISIBLE
            icon3.setImageResource(R.drawable.baseline_expand_less_24)
            if (iscard900) {
                if (listtime.contains("09:00")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1030) {
                if (listtime.contains("10:30")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1200) {
                if (listtime.contains("12:00")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1330) {
                if (listtime.contains("13:30")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1500) {
                if (listtime.contains("15:00")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1630) {
                if (listtime.contains("16:30")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1800) {
                if (listtime.contains("18:00")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard1930) {
                if (listtime.contains("19:30")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }
            if (iscard2100) {
                if (listtime.contains("21:00")) {
                    if (list.contains("field3")) {
                        expandButton3.visibility = View.GONE
                        tv3.setText("Booked")
                    }
                }
            }


        }
    }


}
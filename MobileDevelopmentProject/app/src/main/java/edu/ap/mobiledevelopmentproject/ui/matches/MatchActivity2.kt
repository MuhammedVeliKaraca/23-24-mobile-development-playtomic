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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MatchActivity2 : AppCompatActivity() {
    var hallid:String = ""
    var name:String = ""
    var image:String = ""
    var location:String = ""
    var date:String = ""
    lateinit var imageview: ImageView
    lateinit var tvname: TextView
    lateinit var tvlocation: TextView
    private lateinit var expandButton: Button
    private lateinit var icon: ImageView

    private lateinit var expandButton2: Button
    private lateinit var icon2: ImageView

    private lateinit var expandButton3: Button
    private lateinit var icon3: ImageView


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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match2)

        simpleDatePicker = findViewById(R.id.simpleDatePicker);
       val calendar:Calendar = Calendar.getInstance()
        simpleDatePicker!!.init(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONDAY),calendar.get(Calendar.DAY_OF_MONTH),null)

        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        date = dateFormat.format(currentDate).toString()
        // Set the OnDateChangedListener
        simpleDatePicker!!.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            // Handle date changes here
            date = dayOfMonth.toString()+"/"+monthOfYear+"/"+year

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
        // You can set any color you want here
        val newColor = ContextCompat.getColor(this, R.color.gray)
        val oldColor = ContextCompat.getColor(this, R.color.white)
        card900.setOnClickListener({
            // Change the background color of the card
            card900.setCardBackgroundColor(newColor)
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
        })

        card1030.setOnClickListener({
            // Change the background color of the card
            card1030.setCardBackgroundColor(newColor)
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
        })
 card1200.setOnClickListener({
            // Change the background color of the card
            card1200.setCardBackgroundColor(newColor)
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
        })

 card1330.setOnClickListener({
            // Change the background color of the card
            card1330.setCardBackgroundColor(newColor)
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
        })

 card1500.setOnClickListener({
            // Change the background color of the card
            card1500.setCardBackgroundColor(newColor)
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
        })
 card1630.setOnClickListener({
            // Change the background color of the card
            card1630.setCardBackgroundColor(newColor)
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
        })

 card1800.setOnClickListener({
            // Change the background color of the card
            card1800.setCardBackgroundColor(newColor)
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
        })


 card1930.setOnClickListener({
            // Change the background color of the card
            card1930.setCardBackgroundColor(newColor)
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
        })
 card2100.setOnClickListener({
            // Change the background color of the card
            card2100.setCardBackgroundColor(newColor)
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
        })


        expandButton.setOnClickListener({
            var time:String? = ""
             if(iscard900) time = "09:00"
             if(iscard1030) time = "10:30"
             if(iscard1200) time = "12:00"
             if(iscard1330) time = "13:30"
             if(iscard1500) time = "15:00"
             if(iscard1630) time = "16:30"
             if(iscard1800) time = "18:00"
             if(iscard1930) time = "19:30"
             if(iscard2100) time = "21:00"

            val map: HashMap<String, Any> = HashMap()
            map["date"] = date
            map["time"] = time.toString()
            map["id"] = hallid
            map["name"] = name
            map["location"] = location
            map["userid"] = FirebaseAuth.getInstance().currentUser!!.uid
            map["useremail"] = FirebaseAuth.getInstance().currentUser!!.email.toString()
            map["imageUrl"] = image

            FirebaseDatabase.getInstance().getReference().child("Booking").push().setValue(map)
            Toast.makeText(this@MatchActivity2,"Booking done",Toast.LENGTH_LONG).show()
        })


        var id  = intent.getStringExtra("id");
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
                    TODO("Not yet implemented")
                }
            })

        val back:ImageView? = findViewById(R.id.back)
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
    }

    private fun toggleButtonVisibility() {
        if (expandButton.visibility == View.VISIBLE) {
            expandButton.visibility = View.GONE
            icon.setImageResource(R.drawable.baseline_expand_more_24)

        } else {
            expandButton.visibility = View.VISIBLE
            icon.setImageResource(R.drawable.baseline_expand_less_24)
        }
    }
private fun toggleButtonVisibility2() {
        if (expandButton2.visibility == View.VISIBLE) {
            expandButton2.visibility = View.GONE
            icon2.setImageResource(R.drawable.baseline_expand_more_24)

        } else {
            expandButton2.visibility = View.VISIBLE
            icon2.setImageResource(R.drawable.baseline_expand_less_24)
        }
    }
private fun toggleButtonVisibility3() {
        if (expandButton3.visibility == View.VISIBLE) {
            expandButton3.visibility = View.GONE
            icon3.setImageResource(R.drawable.baseline_expand_more_24)

        } else {
            expandButton3.visibility = View.VISIBLE
            icon3.setImageResource(R.drawable.baseline_expand_less_24)
        }
    }



}
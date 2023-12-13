package edu.ap.mobiledevelopmentproject.ui.matches

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.ap.mobiledevelopmentproject.R

class MatchActivity2 : AppCompatActivity() {
    lateinit var imageview: ImageView
    lateinit var tvname: TextView
    lateinit var tvlocation: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match2)
        val id  = intent.getStringExtra("id");
        imageview = findViewById(R.id.imageview)
        tvname = findViewById(R.id.tv)
        tvlocation = findViewById(R.id.location)
        FirebaseDatabase.getInstance().getReference().child("Halls").child(id.toString())
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(ds: DataSnapshot) {

                        val id = ds.key.toString()
                        val name = ds.child("name").getValue().toString()
                        val image = ds.child("image").getValue().toString()
                        val location = ds.child("location").getValue().toString()

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



    }
}
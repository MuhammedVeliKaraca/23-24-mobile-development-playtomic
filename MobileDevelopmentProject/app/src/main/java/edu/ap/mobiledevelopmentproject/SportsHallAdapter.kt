package edu.ap.mobiledevelopmentproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.ap.mobiledevelopmentproject.ui.matches.MatchActivity2

class SportsHallAdapter(private val context: Context, private val sportsHalls: List<SportsHall>) :
    RecyclerView.Adapter<SportsHallAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.halls_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sportsHall = sportsHalls[position]

        // Set data to views
        holder.nameTextView.text = sportsHall.name
        holder.locationTextView.text = sportsHall.location

        // Load image using Glide (you need to add the Glide dependency to your project)
        Glide.with(context)
            .load(sportsHall.image)
            .into(holder.imageView)

        if (FirebaseAuth.getInstance().currentUser != null) {
            FirebaseDatabase.getInstance().getReference().child("Booking")
                .orderByChild("userid").equalTo(FirebaseAuth.getInstance().currentUser!!.uid)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds: DataSnapshot in snapshot.children) {
                            val date = ds.child("date").getValue(String::class.java)
                            val bookingid = ds.child("id").getValue(String::class.java)
                            val imageUrl = ds.child("imageUrl").getValue(String::class.java)
                            val location = ds.child("location").getValue(String::class.java)
                            val name = ds.child("name").getValue(String::class.java)
                            val time = ds.child("time").getValue(String::class.java)
                            val useremail = ds.child("useremail").getValue(String::class.java)
                            val userid = ds.child("userid").getValue(String::class.java)
                            val field = ds.child("field").getValue(String::class.java)


                            if (bookingid.equals(sportsHall.id.toString())) {


                                val newColor =
                                    ContextCompat.getColor(context, R.color.gray)

                                if (time.equals("09:00")) {

                                    holder.card900.setCardBackgroundColor(newColor)
                                }
                                if (time.equals("10:30")) {


                                    holder.card1030.setCardBackgroundColor(newColor)
                                }

                                if (time.equals("12:00")) {

                                    holder.card1200.setCardBackgroundColor(newColor)
                                }
                                if (time.equals("13:30")) {

                                    holder.card1330.setCardBackgroundColor(newColor)
                                }
                                if (time.equals("15:00")) {

                                    holder.card1500.setCardBackgroundColor(newColor)
                                }
                                if (time.equals("16:30")) {

                                    holder.card1630.setCardBackgroundColor(newColor)
                                }
                                if (time.equals("18:00")) {
                                    holder.card1800.setCardBackgroundColor(newColor)
                                }

                                if (time.equals("19:30")) {
                                    holder.card1930.setCardBackgroundColor(newColor)
                                }
                                if (time.equals("21:00")) {
                                    holder.card2100.setCardBackgroundColor(newColor)
                                }

                            }


                        }

                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })

        }


        // You can set click listeners or perform additional operations here
        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                // Pass the data to the next screen (replace NextActivity::class.java with your target activity)
                val intent = Intent(context, MatchActivity2::class.java)
                intent.putExtra("id", sportsHall.id)
               view!!.context.startActivity(intent)
            }

        })
    }

    override fun getItemCount(): Int {
        return sportsHalls.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val nameTextView: TextView = itemView.findViewById(R.id.name)
        val locationTextView: TextView = itemView.findViewById(R.id.location)
          var card900: MaterialCardView  = itemView.findViewById(R.id.card900)
          var card1030: MaterialCardView  = itemView.findViewById(R.id.card1030)
          var card1200: MaterialCardView  = itemView.findViewById(R.id.card1200)
          var card1330: MaterialCardView  = itemView.findViewById(R.id.card1330)
          var card1500: MaterialCardView  = itemView.findViewById(R.id.card1500)
          var card1630: MaterialCardView  = itemView.findViewById(R.id.card1630)
          var card1800: MaterialCardView  = itemView.findViewById(R.id.card1800)
          var card1930: MaterialCardView  = itemView.findViewById(R.id.card1930)
          var card2100: MaterialCardView  = itemView.findViewById(R.id.card2100)
    }
}

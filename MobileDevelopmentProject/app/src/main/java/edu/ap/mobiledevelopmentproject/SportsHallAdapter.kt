package edu.ap.mobiledevelopmentproject

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
    }
}

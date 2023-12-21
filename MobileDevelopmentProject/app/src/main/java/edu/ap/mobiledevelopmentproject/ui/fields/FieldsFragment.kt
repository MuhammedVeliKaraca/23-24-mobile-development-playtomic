package edu.ap.mobiledevelopmentproject.ui.fields

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import edu.ap.mobiledevelopmentproject.BookingAdapter
import edu.ap.mobiledevelopmentproject.R
import edu.ap.mobiledevelopmentproject.SportsHall
import edu.ap.mobiledevelopmentproject.SportsHallAdapter

class FieldsFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fields, container, false)

        recyclerView = view.findViewById(R.id.recyclerview)
        val layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.layoutManager = layoutManager
        val hallList = mutableListOf<SportsHall>() // Populate this list from Firebase onDataChange

        FirebaseDatabase.getInstance().getReference().child("Halls")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (ds in snapshot.children) {
                        val id = ds.key.toString()
                        val name = ds.child("name").getValue().toString()
                        val image = ds.child("image").getValue().toString()
                        val location = ds.child("location").getValue().toString()
                        val length = ds.child("length").getValue().toString()

                        // Create a Hall instance and add it to the list
                        val hall = SportsHall(id,name,image,location,length)
                        hallList.add(hall)

                    }

                    val bookingAdapter = BookingAdapter(requireActivity(), hallList)
                    recyclerView.adapter = bookingAdapter

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })




        return view
    }
}
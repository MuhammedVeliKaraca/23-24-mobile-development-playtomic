package edu.ap.mobiledevelopmentproject.ui.fields

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.ap.mobiledevelopmentproject.R
import edu.ap.mobiledevelopmentproject.ui.matches.MatchesFragment

class FieldsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fields, container, false)

        val matchesFragment = MatchesFragment()

        val input = view.findViewById<EditText>(R.id.text)
        val button = view.findViewById<Button>(R.id.button)

        button.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("text", input.text.toString())
            bundle.putString("duration", "90min")
            matchesFragment.arguments = bundle

            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainContainer, matchesFragment)
                    .commit()
            }

        }

        return view
    }
}
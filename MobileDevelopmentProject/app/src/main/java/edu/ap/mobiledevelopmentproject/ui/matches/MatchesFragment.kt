package edu.ap.mobiledevelopmentproject.ui.matches

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import edu.ap.mobiledevelopmentproject.R
import edu.ap.mobiledevelopmentproject.databinding.FragmentMatchesBinding
import org.w3c.dom.Text

class MatchesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matches, container, false)

        val text = view.findViewById<TextView>(R.id.text2)
        val data = arguments

        text.text = data?.getString("text")


        return view
    }

}
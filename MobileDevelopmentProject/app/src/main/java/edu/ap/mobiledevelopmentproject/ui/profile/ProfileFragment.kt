package edu.ap.mobiledevelopmentproject.ui.profile

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
import edu.ap.mobiledevelopmentproject.databinding.FragmentProfileBinding
import edu.ap.mobiledevelopmentproject.ui.matches.MatchesFragment

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val name = "John Smith"
    private val description = "Plays in Mortsel, Belgium"
    private val matches = 65
    private val followers = 351
    private val following = 90

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        binding.name.text = name
        binding.description.text = description

        // Todo: stats
//        val statsText = "Matches: $matches | Followers: $followers | Following: $following"
//        binding.statsTextView.text = statsText

        // Set up listeners or additional UI elements as needed

    }

}
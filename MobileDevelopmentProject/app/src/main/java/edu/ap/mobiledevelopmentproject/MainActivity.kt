package edu.ap.mobiledevelopmentproject

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import edu.ap.mobiledevelopmentproject.databinding.ActivityMainBinding
import edu.ap.mobiledevelopmentproject.ui.fields.FieldsFragment
import edu.ap.mobiledevelopmentproject.ui.matches.MatchesFragment
import edu.ap.mobiledevelopmentproject.ui.profile.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadFragment(MatchesFragment())

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_matches -> {
                    loadFragment(MatchesFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_fields -> {
                    loadFragment(FieldsFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainContainer, fragment)
            addToBackStack(null) // Optionally, add to back stack for navigation
            commit()
        }
    }

}
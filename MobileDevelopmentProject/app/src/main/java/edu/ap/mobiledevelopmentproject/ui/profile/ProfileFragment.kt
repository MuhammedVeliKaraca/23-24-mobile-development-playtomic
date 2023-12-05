import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import edu.ap.mobiledevelopmentproject.R
import edu.ap.mobiledevelopmentproject.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var editName: EditText
    private lateinit var editDescription: EditText
    private lateinit var saveChangesButton: Button
    private lateinit var cancelChangesButton: Button

    private lateinit var userId: String // Replace this with the actual user ID

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
        // Initialize UI elements
        editName = view?.findViewById(R.id.editName)!!
        editDescription = view?.findViewById(R.id.editDescription)!!
        saveChangesButton = view?.findViewById(R.id.saveChangesButton)!!
        cancelChangesButton = view?.findViewById(R.id.cancelChangesButton)!!

        // Handle Edit Profile Button Click
        view?.findViewById<Button>(R.id.editProfileButton)?.setOnClickListener {
            // Show edit fields and buttons
            showEditFields(true)

            // Populate edit fields with current data
            val currentName = view?.findViewById<TextView>(R.id.name)?.text.toString()
            val currentDescription = view?.findViewById<TextView>(R.id.description)?.text.toString()

            editName.setText(currentName)
            editDescription.setText(currentDescription)
        }

        // Handle Save Changes Button Click
        saveChangesButton.setOnClickListener {
            // Save changes to Firebase database
            val newName = editName.text.toString()
            val newDescription = editDescription.text.toString()

            // Update the UI with new data
            view?.findViewById<TextView>(R.id.name)?.text = newName
            view?.findViewById<TextView>(R.id.description)?.text = newDescription

            // Hide edit fields and buttons
            showEditFields(false)

            // Save changes to Firebase
            saveChangesToFirebase(userId, newName, newDescription)
        }

        // Handle Cancel Changes Button Click
        cancelChangesButton.setOnClickListener {
            // Hide edit fields and buttons
            showEditFields(false)
        }
    }

    private fun showEditFields(show: Boolean) {
        val visibility = if (show) View.VISIBLE else View.GONE
        editName.visibility = visibility
        editDescription.visibility = visibility
        saveChangesButton.visibility = visibility
        cancelChangesButton.visibility = visibility
    }

    private fun saveChangesToFirebase(userId: String, newName: String, newDescription: String) {
        try {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val usersRef: DatabaseReference = database.getReference("users")

            // Replace "userId" with the actual user ID
            val userRef: DatabaseReference = usersRef.child(userId)

            // Update user data
            userRef.child("name").setValue(newName)
            userRef.child("description").setValue(newDescription)

            Log.d("ProfileFragment", "Changes saved to Firebase")
        } catch (e: Exception) {
            Log.e("ProfileFragment", "Error saving changes to Firebase: ${e.message}")
        }
    }
}

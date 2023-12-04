package edu.ap.mobiledevelopmentproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Register : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var progressBar: ProgressBar
    private lateinit var textView: TextView

/*    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            val intent = Intent(this@Register, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = FirebaseAuth.getInstance()
        editTextEmail = findViewById(R.id.email)
        editTextPassword = findViewById(R.id.password)
        buttonRegister = findViewById(R.id.btn_register)
        progressBar = findViewById(R.id.progressBar)
        textView = findViewById(R.id.loginNow);

        textView.setOnClickListener {
            val intent = Intent(this@Register, Login::class.java)
            startActivity(intent)
            finish()
        }

        buttonRegister.setOnClickListener {

            progressBar.visibility = View.VISIBLE
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this@Register, "Enter email", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                Toast.makeText(this@Register, "Enter password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    progressBar.visibility = View.GONE
                    if (task.isSuccessful) {
                        Toast.makeText(this@Register, "Account created.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Register, "Account creation failed.", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}
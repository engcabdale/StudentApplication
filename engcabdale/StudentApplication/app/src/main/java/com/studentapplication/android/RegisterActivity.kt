package com.studentapplication.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button

    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        database = MyDatabase(this)

        fullNameEditText = findViewById(R.id.fullName)
        emailEditText = findViewById(R.id.email)
        phoneEditText = findViewById(R.id.phone)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirm)
        registerButton = findViewById(R.id.register_btn)

        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString().toIntOrNull()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            if (password == confirmPassword) {
                if (fullName.isNotBlank() && email.isNotBlank() && phone != null && password.isNotBlank()) {
                    val student = Student(fullname = fullName, email = email, phone = phone, password = password)

                    CoroutineScope(Dispatchers.IO).launch {
                        database.studentDAO().saveStudent(student)
                        runOnUiThread {
                            Toast.makeText(this@RegisterActivity, "Student registered successfully!", Toast.LENGTH_LONG).show()
                            clearInputFields()
                            navigateToNewStudentActivity()
                        }
                    }
                } else {
                    Toast.makeText(this, "Please fill all fields correctly.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun clearInputFields() {
        fullNameEditText.text.clear()
        emailEditText.text.clear()
        phoneEditText.text.clear()
        passwordEditText.text.clear()
        confirmPasswordEditText.text.clear()
    }

    private fun navigateToNewStudentActivity() {
        val intent = Intent(this, NewStudentActivity::class.java)
        startActivity(intent)
    }
}

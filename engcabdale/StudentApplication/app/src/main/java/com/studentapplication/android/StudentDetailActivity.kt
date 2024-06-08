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

class StudentDetailActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button

    private lateinit var database: MyDatabase
    private var studentId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_detail)

        database = MyDatabase(this)

        fullNameEditText = findViewById(R.id.fullName)
        emailEditText = findViewById(R.id.email)
        phoneEditText = findViewById(R.id.phone)
        passwordEditText = findViewById(R.id.password)
        updateButton = findViewById(R.id.update_btn)
        deleteButton = findViewById(R.id.delete_btn)

        studentId = intent.getIntExtra("studentId", 0)

        CoroutineScope(Dispatchers.IO).launch {
            val student = database.studentDAO().getStudentById(studentId)
            runOnUiThread {
                fullNameEditText.setText(student.fullname)
                emailEditText.setText(student.email)
                phoneEditText.setText(student.phone.toString())
                passwordEditText.setText(student.password)
            }
        }

        updateButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString()
            val email = emailEditText.text.toString()
            val phone = phoneEditText.text.toString().toIntOrNull()
            val password = passwordEditText.text.toString()

            if (fullName.isNotBlank() && email.isNotBlank() && phone != null && password.isNotBlank()) {
                val student = Student(id = studentId, fullname = fullName, email = email, phone = phone, password = password)

                CoroutineScope(Dispatchers.IO).launch {
                    database.studentDAO().updateStudent(student)
                    runOnUiThread {
                        Toast.makeText(this@StudentDetailActivity, "Student updated successfully!", Toast.LENGTH_LONG).show()
                        navigateToNewStudentActivity()
                    }
                }
            } else {
                Toast.makeText(this, "Please fill all fields correctly.", Toast.LENGTH_LONG).show()
            }
        }

        deleteButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val studentId = intent.getIntExtra("studentId", -1)
                val student = database.studentDAO().getStudentById(studentId)
                database.studentDAO().deleteStudent(student)
                runOnUiThread {
                    Toast.makeText(this@StudentDetailActivity, "Student deleted successfully!", Toast.LENGTH_LONG).show()
                    navigateToNewStudentActivity()
                }
            }
        }
    }

    private fun navigateToNewStudentActivity() {
        val intent = Intent(this, NewStudentActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}

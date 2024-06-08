package com.studentapplication.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Find the register button in your layout
        val registerButton= findViewById<Button>(R.id.get_started_btn)

        // Set a click listener for the register button
        registerButton.setOnClickListener {
            // Create an Intent to start the RegisterActivity
            val intent = Intent(this, RegisterActivity::class.java)

            // Start the RegisterActivity
            startActivity(intent)
        }
    }
}
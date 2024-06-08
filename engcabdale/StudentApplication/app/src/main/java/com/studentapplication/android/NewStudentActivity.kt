package com.studentapplication.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewStudentActivity : AppCompatActivity() {

    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_student)

        database = MyDatabase(this)
        studentRecyclerView = findViewById(R.id.student_recycler_view)
        studentRecyclerView.layoutManager = LinearLayoutManager(this)

        loadStudents()
    }

    private fun loadStudents() {
        CoroutineScope(Dispatchers.IO).launch {
            val students = database.studentDAO().getAllStudents()
            runOnUiThread {
                studentRecyclerView.adapter = StudentAdapter(students)
            }
        }
    }
}

package com.studentapplication.android

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val studentList: List<Student>) :
    RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position])
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val studentName: TextView = itemView.findViewById(R.id.student_name_tv)

        fun bind(student: Student) {
            studentName.text = student.fullname
            itemView.setOnClickListener {
                val context = itemView.context
                val intent = Intent(context, StudentDetailActivity::class.java)
                intent.putExtra("studentId", student.id)
                context.startActivity(intent)
            }
        }
    }
}
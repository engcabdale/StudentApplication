package com.studentapplication.android

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val fullname: String,
    val email: String,
    val phone: Int,
    val password: String
)

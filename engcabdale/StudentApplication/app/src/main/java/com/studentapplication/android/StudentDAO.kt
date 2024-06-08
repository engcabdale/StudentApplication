package com.studentapplication.android

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {
    @Query("SELECT * FROM student")
    fun getAllStudents(): List<Student>

    @Query("SELECT * FROM student WHERE id = :studentId LIMIT 1")
    fun getStudentById(studentId: Int): Student

    @Query("SELECT * FROM student WHERE email = :email AND password = :password LIMIT 1")
    fun getStudentByEmailAndPassword(email: String, password: String): Student?

    @Update
    fun updateStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("DELETE FROM student WHERE id = :studentId")
    fun deleteStudentById(studentId: Int)

    @Insert
    fun saveStudent(student: Student)
}



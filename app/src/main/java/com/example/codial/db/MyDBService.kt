package com.example.codial.db

import com.example.codial.models.Courses
import com.example.codial.models.Groups
import com.example.codial.models.Mentors
import com.example.codial.models.Students

interface MyDBService {
    fun addCourse(courses: Courses)
    fun showCourse():List<Courses>
    fun editCourse(courses: Courses):Int
    fun deleteCourse(courses: Courses)
    fun getCourseById(id:Int):Courses

    fun addGroup(groups: Groups)
    fun showGroup():List<Groups>
    fun editGroup(groups: Groups):Int
    fun deleteGroup(groups: Groups)


    fun addMentor(mentors: Mentors)
    fun showMentor():List<Mentors>
    fun editMentor(mentors: Mentors):Int
    fun deleteMentor(mentors: Mentors)
    fun getMentorById(id:Int):Mentors

    fun addStudent(students: Students)
    fun showStudent():List<Students>
    fun editStudent(students: Students):Int
    fun deleteStudent(students: Students)
}
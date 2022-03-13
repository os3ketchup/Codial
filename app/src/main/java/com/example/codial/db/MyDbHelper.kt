package com.example.codial.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import com.example.codial.models.Courses
import com.example.codial.models.Groups
import com.example.codial.models.Mentors
import com.example.codial.models.Students

class MyDbHelper(context: Context):SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION),MyDBService {

    companion object{
        const val DB_NAME = "c6"
        const val DB_VERSION = 1

        const val TABLE_COURSE = "Kurslar"
        const val COURSE_ID = "id"
        const val COURSE_NAME = "name"
        const val COURSE_INFO = "about"


        const val TABLE_GROUPS = "Guruhlar"
        const val GROUP_ID = "id"
        const val GROUP_NAME = "name"
        const val GROUP_AMOUNT_STUDENT = "student_soni"
        const val GROUPS_MENTOR_ID = "mentor_id"
        const val GROUP_TIME = "vaqti"
        const val GROUP_DAY = "kunlari"
        const val GROUPS_COURSE_ID = "kurs_id"

        const val TABLE_MENTORS = "Mentorlar"
        const val MENTOR_ID = "id"
        const val MENTOR_NAME = "ismi"
        const val MENTOR_SURNAME = "familyasi"
        const val MENTOR_PHONE_NUMBER = "telefon_raqami"
        const val MENTORS_COURSE_ID = "kurs_id"

        const val TABLE_STUDENTS = "Talabalar"
        const val STUDENT_ID = "id"
        const val STUDENT_NAME = "ismi"
        const val STUDENT_SURNAME = "familiya"
        const val STUDENT_PHONE_NUMBER = "telefon_raqami"
        const val STUDENT_MENTOR_ID = "mentor_id"
        const val STUDENT_GROUP_ID = "guruh_id"
        const val STUDENT_COURSE_ID = "kurs_id"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val courseQuery = "CREATE TABLE $TABLE_COURSE ($COURSE_ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, $COURSE_NAME TEXT NOT NULL, $COURSE_INFO TEXT NOT NULL) "
        val groupQuery = "CREATE TABLE $TABLE_GROUPS($GROUP_ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,$GROUP_NAME TEXT NOT NULL, $GROUP_DAY TEXT NOT NULL, $GROUP_TIME TEXT NOT NULL,$GROUP_AMOUNT_STUDENT INTEGER NOT NULL,$GROUPS_MENTOR_ID INTEGER NOT NULL, $GROUPS_COURSE_ID INTEGER NOT NULL, FOREIGN KEY ($GROUPS_COURSE_ID) REFERENCES $TABLE_COURSE($COURSE_ID),FOREIGN KEY ($GROUPS_MENTOR_ID) REFERENCES $TABLE_MENTORS($MENTOR_ID))"
        val mentorQuery = "CREATE TABLE $TABLE_MENTORS($MENTOR_ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT, $MENTOR_NAME TEXT NOT NULL, $MENTOR_SURNAME TEXT NOT NULL, $MENTOR_PHONE_NUMBER TEXT NOT NULL, $MENTORS_COURSE_ID INTEGER NOT NULL, FOREIGN KEY ($MENTORS_COURSE_ID) REFERENCES $TABLE_COURSE($COURSE_ID))"
        val studentQuery = "CREATE TABLE $TABLE_STUDENTS($STUDENT_ID INTEGER NOT NULL UNIQUE PRIMARY KEY AUTOINCREMENT,$STUDENT_NAME TEXT NOT NULL, $STUDENT_SURNAME TEXT NOT NULL,$STUDENT_PHONE_NUMBER TEXT NOT NULL, $STUDENT_MENTOR_ID INTEGER NOT NULL, $STUDENT_GROUP_ID INTEGER NOT NULL, $STUDENT_COURSE_ID INTEGER NOT NULL, FOREIGN KEY ($STUDENT_MENTOR_ID) REFERENCES $TABLE_MENTORS ($MENTOR_ID), FOREIGN KEY ($STUDENT_GROUP_ID) REFERENCES $TABLE_GROUPS($GROUP_ID), FOREIGN KEY ($STUDENT_COURSE_ID) REFERENCES $TABLE_COURSE ($COURSE_ID))"
        p0?.execSQL(courseQuery)
        p0?.execSQL(groupQuery)
        p0?.execSQL(mentorQuery)
        p0?.execSQL(studentQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun addCourse(courses: Courses) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COURSE_NAME,courses.name)
        contentValues.put(COURSE_INFO,courses.aboutCourses)
        database.insert(TABLE_COURSE,null,contentValues)
        database.close()
    }

    override fun showCourse(): List<Courses> {
        val list = ArrayList<Courses>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_COURSE"
        val cursor = database.rawQuery(query,null)
        if(cursor.moveToFirst()){
            do {
                val courses = Courses(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
                list.add(courses)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editCourse(courses: Courses): Int {
        TODO("Not yet implemented")
    }

    override fun deleteCourse(courses: Courses) {
        TODO("Not yet implemented")
    }

    override fun getCourseById(id: Int): Courses {
        val database = this.readableDatabase
        val cursor = database.query(TABLE_COURSE, arrayOf(COURSE_ID, COURSE_NAME, COURSE_INFO),"$COURSE_ID = ?",
            arrayOf(id.toString()),null,null,null)
        cursor.moveToFirst()
        val course = Courses(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2)
        )
        return course
    }

    override fun addGroup(groups: Groups) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(GROUP_NAME,groups.name)
        contentValues.put(GROUPS_MENTOR_ID,groups.mentors?.id)
        contentValues.put(GROUP_TIME,groups.timeStarting)
        contentValues.put(GROUP_DAY,groups.dataAttending)
        contentValues.put(GROUPS_COURSE_ID,groups.courses?.id)
        contentValues.put(GROUP_AMOUNT_STUDENT,groups.amountStudent)
        database.insert(TABLE_GROUPS,null,contentValues)
        database.close()
    }

    override fun showGroup(): List<Groups> {
        val list = ArrayList<Groups>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_GROUPS"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val groups = Groups(
                    cursor.getInt(0),
                    cursor.getString(1),
                    getMentorById(cursor.getInt(2)),
                    cursor.getString(3),
                    cursor.getString(4),
                    getCourseById(cursor.getInt(5)),
                    cursor.getInt(6)
                )
                list.add(groups)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editGroup(groups: Groups): Int {
        TODO("Not yet implemented")
    }

    override fun deleteGroup(groups: Groups) {
        TODO("Not yet implemented")
    }



    override fun addMentor(mentors: Mentors) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MENTOR_NAME,mentors.firstname)
        contentValues.put(MENTOR_SURNAME,mentors.surname)
        contentValues.put(MENTOR_PHONE_NUMBER,mentors.phoneNumber)
        contentValues.put(MENTORS_COURSE_ID,mentors.courses?.id)
        database.insert(TABLE_MENTORS,null,contentValues)
        database.close()
    }

    override fun showMentor(): List<Mentors> {
        val list = ArrayList<Mentors>()
        val database = this.readableDatabase
        val query = "select * from $TABLE_MENTORS"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                val mentors = Mentors(
                    cursor.getInt(0),
                    cursor.getString(1),cursor.getString(2),
                    cursor.getString(3),
                    getCourseById(cursor.getInt(4))
                )
                list.add(mentors)
            }while (cursor.moveToNext())
        }
        return list
    }

    override fun editMentor(mentors: Mentors): Int {
        TODO("Not yet implemented")
    }

    override fun deleteMentor(mentors: Mentors) {
        TODO("Not yet implemented")
    }

    override fun getMentorById(id: Int): Mentors {
        val database = this.readableDatabase
        val cursor = database.query(
            TABLE_MENTORS, arrayOf(
                MENTOR_ID, MENTOR_NAME, MENTOR_SURNAME, MENTOR_PHONE_NUMBER,
                 MENTORS_COURSE_ID),"$MENTOR_ID = ?",
            arrayOf(id.toString()),null,null,null)
        cursor.moveToFirst()
        val mentors = Mentors(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3),
            getCourseById(cursor.getInt(4))
        )
        return mentors
    }

    override fun addStudent(students: Students) {
        TODO("Not yet implemented")
    }

    override fun showStudent(): List<Students> {
        TODO("Not yet implemented")
    }

    override fun editStudent(students: Students): Int {
        TODO("Not yet implemented")
    }

    override fun deleteStudent(students: Students) {
        TODO("Not yet implemented")
    }


}
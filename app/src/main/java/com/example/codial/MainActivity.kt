package com.example.codial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.codial.adapter.MyCourseAdapter
import com.example.codial.databinding.ActivityMainBinding
import com.example.codial.db.MyDbHelper
import com.example.codial.models.Courses

class MainActivity : AppCompatActivity() {
    lateinit var myDbHelper: MyDbHelper
    lateinit var binding: ActivityMainBinding
    lateinit var courses: Courses
    lateinit var myCourseAdapter: MyCourseAdapter
    lateinit var list: ArrayList<Courses>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*
        list = ArrayList()
        myDbHelper = MyDbHelper(this)
        binding.apply {
            btnCourseSave.setOnClickListener {
                val name = etName.text.toString()
                val about = etInfo.text.toString()
                val courses = Courses(name, about)
                myDbHelper.addCourse(courses)
                Toast.makeText(this@MainActivity, "saved $name $about", Toast.LENGTH_SHORT).show()
            }
        }
        list.addAll(myDbHelper.showCourse())
        myCourseAdapter = MyCourseAdapter(list)
        binding.rvCourse.adapter = myCourseAdapter*/
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.navigation_host_view).navigateUp()
    }
}
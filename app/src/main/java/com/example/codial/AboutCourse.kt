package com.example.codial

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.codial.databinding.FragmentAboutCourseBinding
import com.example.codial.models.Courses
import kotlinx.android.synthetic.main.fragment_about_course.*
import java.security.acl.Group


class AboutCourse : Fragment() {
    lateinit var binding: FragmentAboutCourseBinding
    lateinit var courses: Courses




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       courses = requireArguments().getSerializable("itemId") as Courses
        tv_about.text = courses.aboutCourses
        about_course_toolbar.title = courses.name
        toolbar_delete_icon.setOnClickListener {
            Toast.makeText(context, "opened", Toast.LENGTH_SHORT).show()
            val dialog = AlertDialog.Builder(context).create()
            val deleteCourseDialog = LayoutInflater.from(context).inflate(R.layout.delete_course_dialog,null,false)

            dialog.setView(deleteCourseDialog)
            dialog.show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAboutCourseBinding.inflate(inflater,container,false)


        return binding.root
    }


}
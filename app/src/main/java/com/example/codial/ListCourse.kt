package com.example.codial

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.codial.adapter.MyCourseAdapter
import com.example.codial.databinding.CourseDialogBinding
import com.example.codial.databinding.FragmentListCourseBinding
import com.example.codial.databinding.ToolbarBinding
import com.example.codial.db.MyDbHelper
import com.example.codial.models.Courses
import kotlinx.android.synthetic.main.course_dialog.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class ListCourse : Fragment() {
    lateinit var binding: FragmentListCourseBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var courseAdapter: MyCourseAdapter
    lateinit var list: ArrayList<Courses>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentListCourseBinding.inflate(inflater,container,false)
        list = ArrayList()
        myDbHelper = MyDbHelper(binding.root.context)
        update()




        binding.toolbarAddIcon.setOnClickListener {
            Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val courseDialog = LayoutInflater.from(binding.root.context).inflate(R.layout.course_dialog,null,false)
            courseDialog.tv_save.setOnClickListener {
                val name = courseDialog.et_name_course.text.toString()
                val aboutCourse = courseDialog.about_course.text.toString()
                val courses = Courses(name,aboutCourse)
                myDbHelper.addCourse(courses)
                update()
                dialog.cancel()
            }

            dialog.setView(courseDialog)

            dialog.show()

        }








        return binding.root
    }

    private fun update() {
        list = myDbHelper.showCourse() as ArrayList<Courses>

        courseAdapter = MyCourseAdapter(list,object :MyCourseAdapter.RvClick{
            override fun itemClick(courses: Courses) {

                binding.root.findNavController().navigate(R.id.aboutCourse,Bundle().apply {
                    putSerializable("itemId",courses)
                })
            }
        })
        binding.rvCourseName.adapter = courseAdapter
    }


}
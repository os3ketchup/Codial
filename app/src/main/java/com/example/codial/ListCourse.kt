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

/**
 * A simple [Fragment] subclass.
 * Use the [ListCourse.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListCourse : Fragment() {
    lateinit var binding: FragmentListCourseBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var courseAdapter: MyCourseAdapter
    lateinit var list: ArrayList<Courses>

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListCourse.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListCourse().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.codial

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.codial.adapter.MyCourseAdapter
import com.example.codial.databinding.FragmentListMentorBinding
import com.example.codial.db.MyDbHelper
import com.example.codial.models.Courses


class ListMentor : Fragment() {
    lateinit var mentorBinding: FragmentListMentorBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var courseAdapter: MyCourseAdapter
    lateinit var list: ArrayList<Courses>







    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mentorBinding = FragmentListMentorBinding.inflate(inflater,container,false)
        list = ArrayList()
        myDbHelper = MyDbHelper(mentorBinding.root.context)
        update()



        return mentorBinding.root
    }

    private fun update() {
        list = myDbHelper.showCourse() as ArrayList<Courses>

        courseAdapter = MyCourseAdapter(list,object :MyCourseAdapter.RvClick{
            override fun itemClick(courses: Courses) {
                mentorBinding.root.findNavController().navigate(R.id.scheduleMentors,Bundle().apply {
                    putSerializable("itemCourse",courses)

                })
            }
        })
        mentorBinding.rvCourseName.adapter = courseAdapter
    }


}
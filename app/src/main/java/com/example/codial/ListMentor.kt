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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ListMentor.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListMentor : Fragment() {
    lateinit var mentorBinding: FragmentListMentorBinding
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
                mentorBinding.root.findNavController().navigate(R.id.scheduleMentors)
            }
        })
        mentorBinding.rvCourseName.adapter = courseAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListMentor.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListMentor().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
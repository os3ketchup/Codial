package com.example.codial

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.codial.adapter.MyMentorAdapter
import com.example.codial.databinding.FragmentScheduleMentorsBinding
import com.example.codial.db.MyDbHelper
import com.example.codial.models.Courses
import com.example.codial.models.Mentors
import kotlinx.android.synthetic.main.item_mentor.*
import kotlinx.android.synthetic.main.mentor_dialog.view.*
import java.util.ArrayList


class ScheduleMentors : Fragment() {
    lateinit var binding: FragmentScheduleMentorsBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var myMentorAdapter: MyMentorAdapter
    lateinit var list: ArrayList<Mentors>
    lateinit var courses: Courses




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        courses = requireArguments().getSerializable("itemCourse") as Courses
        binding.scheduleMentorsToolbar.title = courses.name



        binding.ivScheduleAdd.setOnClickListener {
            Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val mentorDialog = LayoutInflater.from(binding.root.context)
                .inflate(R.layout.mentor_dialog, null, false)
            mentorDialog.btn_save_mentor.setOnClickListener {
                val name = mentorDialog.et_field_mentor_name.text.toString()
                val surname = mentorDialog.et_field_mentor_surname.text.toString()
                val phoneNumber = mentorDialog.et_mentor_phone_number.text.toString()
                val mentors = Mentors(name, surname, phoneNumber, courses)
                myDbHelper.addMentor(mentors)
                update()
                dialog.cancel()
            }

            dialog.setView(mentorDialog)
            dialog.show()
        }
        update()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentScheduleMentorsBinding.inflate(inflater, container, false)








        return binding.root
    }

    private fun update() {
        val mList = ArrayList<Mentors>()
        list = ArrayList()
        myDbHelper = MyDbHelper(binding.root.context)
        mList.addAll(myDbHelper.showMentor())
        mList.forEach {
            if (it.courses?.id == courses.id) {
                list.add(it)
            }
        }

        myMentorAdapter = MyMentorAdapter(list, object : MyMentorAdapter.RvClick {
            override fun itemClick(mentors: Mentors) {
                Bundle().apply {
                    putSerializable("mentorId",mentors)
                }
            }
        })
        binding.rvScheduleMentor.adapter = myMentorAdapter
    }


}
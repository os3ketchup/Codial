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
import com.example.codial.models.Mentors
import kotlinx.android.synthetic.main.item_mentor.*
import kotlinx.android.synthetic.main.mentor_dialog.view.*
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScheduleMentors.newInstance] factory method to
 * create an instance of this fragment.
 */
 var mList = ArrayList<Mentors>()
class ScheduleMentors : Fragment() {
    lateinit var binding: FragmentScheduleMentorsBinding
    lateinit var myDbHelper: MyDbHelper
    lateinit var myMentorAdapter: MyMentorAdapter
    lateinit var list: ArrayList<Mentors>


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentScheduleMentorsBinding.inflate(inflater,container,false)
        list = ArrayList()
        myDbHelper = MyDbHelper(binding.root.context)
        update()



        binding.ivScheduleAdd.setOnClickListener {
            Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
            val dialog = AlertDialog.Builder(binding.root.context).create()
            val mentorDialog = LayoutInflater.from(binding.root.context).inflate(R.layout.mentor_dialog,null,false)
            mentorDialog.btn_save_mentor.setOnClickListener {
                val name = mentorDialog.et_field_mentor_name.text.toString()
                val surname = mentorDialog.et_field_mentor_surname.text.toString()
                val phoneNumber = mentorDialog.et_mentor_phone_number.text.toString()
                val mentors = Mentors(name,surname,phoneNumber)
                myDbHelper.addMentor(mentors)
                update()
                dialog.cancel()
            }
            dialog.setView(mentorDialog)
            dialog.show()
        }

        return binding.root
    }

    private fun update() {
        list = myDbHelper.showMentor() as ArrayList<Mentors>
        myMentorAdapter = MyMentorAdapter(list,object:MyMentorAdapter.RvClick{
            override fun itemClick(mentors: Mentors) {
            }
        })
        binding.rvScheduleMentor.adapter = myMentorAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ScheduleMentors.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ScheduleMentors().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
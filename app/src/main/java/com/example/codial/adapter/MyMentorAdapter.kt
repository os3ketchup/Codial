package com.example.codial.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codial.databinding.ItemMentorBinding
import com.example.codial.models.Mentors

class MyMentorAdapter(var list: List<Mentors>,val rvClick: RvClick): RecyclerView.Adapter<MyMentorAdapter.VH>() {

    inner class VH(private var itemRV: ItemMentorBinding):RecyclerView.ViewHolder(itemRV.root){
        fun onBind(mentors: Mentors){
                itemRV.tvNameMentor.text = mentors.firstname
                itemRV.tvSurnameMentor.text = mentors.surname
                itemRV.tvPhoneNumberMentor.text = mentors.phoneNumber
                    rvClick.itemClick(mentors)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemMentorBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
        interface RvClick{
            fun itemClick(mentors: Mentors)
        }
}
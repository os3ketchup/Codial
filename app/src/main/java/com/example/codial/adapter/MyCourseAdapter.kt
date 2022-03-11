package com.example.codial.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codial.databinding.ItemGroupBinding
import com.example.codial.models.Courses

class MyCourseAdapter(var list: List<Courses>,val rvClick: RvClick): RecyclerView.Adapter<MyCourseAdapter.VH>() {

    inner class VH(private var itemRV:ItemGroupBinding):RecyclerView.ViewHolder(itemRV.root){
        fun onBind(courses: Courses){
            itemRV.tvCourseName.text = courses.name
            itemRV.root.setOnClickListener {
                rvClick.itemClick(courses)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemGroupBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface RvClick{
        fun itemClick(courses: Courses)
    }
}
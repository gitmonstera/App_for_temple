package com.example.apptemple.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.DataClasses.LessonsData
import com.example.apptemple.databinding.ItemLessonsBinding

class LessonsAdapter(private val lessonsItem: List<LessonsData>) :
    RecyclerView.Adapter<LessonsAdapter.LessonsViewHolder>() {

    inner class LessonsViewHolder(private val binding: ItemLessonsBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bind(lessons: LessonsData) {
                binding.lessonTitle.text = lessons.lessonsTitle
                binding.lessonContent.text = lessons.lessonsContent
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonsViewHolder {
        val binding = ItemLessonsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LessonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LessonsViewHolder, position: Int) {
        holder.bind(lessonsItem[position])
    }

    override fun getItemCount(): Int = lessonsItem.size
}
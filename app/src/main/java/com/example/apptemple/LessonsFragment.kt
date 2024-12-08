package com.example.apptemple

import androidx.core.content.res.ResourcesCompat
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptemple.Adapters.LessonsAdapter
import com.example.apptemple.DataClasses.LessonsData
import com.example.apptemple.databinding.FragmentLessonsBinding

class LessonsFragment : Fragment() {
    private lateinit var binding: FragmentLessonsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentLessonsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lessonsAdapter()
    }

    private fun lessonsAdapter() {
        val lessonsRecyclerView = binding.lessonsSlider
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        lessonsRecyclerView.layoutManager = layoutManager

        val lessonsItems = listOf(
            LessonsData("Шахматы", "Это описание, тут ходят"),
            LessonsData("Волейбол", "Это описание, тут мяч кидают"),
            LessonsData("Плавание", "Это описание, тут плавают")
        )

        val lessonsAdapter = LessonsAdapter(lessonsItems)
        binding.lessonsSlider.adapter = lessonsAdapter
    }
}
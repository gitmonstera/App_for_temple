package com.example.apptemple

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.apptemple.DataClasses.AnnounceData
import com.example.apptemple.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    //Функция с конфигурацией запуска (тут менять нечего)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        announceSliderAdapter()
    }

    private fun announceSliderAdapter() {
        val recyclerView = binding.announceSlider
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager

        val announceItems = listOf(
            AnnounceData(R.mipmap.justannounce, "Это первый анонс"),
            AnnounceData(R.mipmap.justannounce2, "Это второй анонс"),
            AnnounceData(R.mipmap.justannounce3, "Это третий анонс"),
            AnnounceData(R.mipmap.justannounce4, "Это четвертый анонс"),
            AnnounceData(R.mipmap.justannounce5, "Это пятый анонс")
        )

        val adapter = AnnounceAdapter(announceItems)
        recyclerView.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }
}
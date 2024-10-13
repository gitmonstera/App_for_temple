package com.example.apptemple

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.DataClasses.AnnounceData
import com.example.apptemple.DataClasses.NewsData
import com.example.apptemple.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private var currentPosition = 0

    //Функция с конфигурацией запуска (тут менять нечего)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        announceSliderAdapter()

        buttonsSlide()
        newsUpdater()
    }

    private fun announceSliderAdapter() {
        val announceRecyclerView = binding.announceSlider
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        announceRecyclerView.layoutManager = layoutManager

        val announceItems = listOf(
            AnnounceData("Первый анонс", R.mipmap.justannounce),
            AnnounceData("Второй анонс", R.mipmap.justannounce2),
            AnnounceData("Третий анонс", R.mipmap.justannounce3),
            AnnounceData("Четвертый анонс", R.mipmap.justannounce4),
            AnnounceData("Пятый анонс", R.mipmap.justannounce5)
        )

        val adapter = AnnounceAdapter(announceItems)
        announceRecyclerView.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(announceRecyclerView)
    }

    private fun buttonsSlide() {
        binding.forwardArrow.setOnClickListener {
            val itemCount = binding.announceSlider.adapter?.itemCount ?: 0
            if (currentPosition < itemCount - 1) {
                currentPosition++
                binding.announceSlider.smoothScrollToPosition(currentPosition)
            }
        }

        binding.backArrow.setOnClickListener {
            if (currentPosition > 0) {
                currentPosition--
                binding.announceSlider.smoothScrollToPosition(currentPosition)
            }
        }
    }

    private fun newsUpdater() {
        val newsRecyclerView = binding.newsSlider
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        newsRecyclerView.layoutManager = layoutManager

        val newItems = listOf(
            NewsData("Первый пост", "Описание первого поста", R.mipmap.justannounce),
            NewsData("Второй пост", "Описание второго поста", R.mipmap.justannounce2),
            NewsData("Третий пост", "Описание третьего поста", R.mipmap.justannounce3),
            NewsData("Четвертый пост", "Описание четвертого поста", R.mipmap.justannounce4),
            NewsData("Пятый пост", "Описание пятого поста", R.mipmap.justannounce5)

        )
        val adapter = NewsAdapter(newItems)
        newsRecyclerView.adapter = adapter

        newsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView : RecyclerView, dx : Int, dy : Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (dy > 0) {
                    binding.announceSlider.animate()
                        .translationY(-binding.announceSlider.height.toFloat())
                        .alpha(0f)
                        .setDuration(200)
                        .withEndAction { binding.announceSlider.visibility = View.GONE }
                        .start()

                    binding.backArrow.animate()
                        .translationY(-binding.backArrow.height.toFloat())
                        .alpha(0f)
                        .setDuration(300)
                        .start()

                    binding.forwardArrow.animate()
                        .translationY(-binding.forwardArrow.height.toFloat())
                        .alpha(0f)
                        .setDuration(300)
                        .start()
                }else if (dy < 0) {
                    binding.announceSlider.animate()
                        .translationY(0f)
                        .alpha(1f)
                        .setDuration(200)
                        .withStartAction { binding.announceSlider.visibility = View.VISIBLE }
                        .start()

                    binding.backArrow.animate()
                        .translationY(0f)
                        .alpha(1f)
                        .setDuration(300)
                        .start()

                    binding.forwardArrow.animate()
                        .translationY(0f)
                        .alpha(1f)
                        .setDuration(300)
                        .start()
                }
            }
        })
    }
}
package com.example.apptemple

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.APIServices.NewsDataInterface
import com.example.apptemple.Adapters.AnnounceAdapter
import com.example.apptemple.Adapters.NewsAdapter
import com.example.apptemple.DataClasses.AnnounceData
import com.example.apptemple.DataClasses.NewsData
import com.example.apptemple.Retrofit.RetrofitClient.newsApi
import com.example.apptemple.databinding.AnnouncesDetailsFragmentBinding
import com.example.apptemple.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var dialogBinding : AnnouncesDetailsFragmentBinding
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

        val adapter = AnnounceAdapter(announceItems, object : AnnounceAdapter.OnItemClickListener {
            override fun onItemClick(announce : AnnounceData) {
                announceDialog(announce.announceImageData, announce.announceTitleData)
            }
        })
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

    private fun announceDialog(image: Int, title:String) {
        val dialog = Dialog(requireContext())
        dialogBinding = AnnouncesDetailsFragmentBinding.inflate(LayoutInflater.from(requireContext()))
        dialog.setContentView(dialogBinding.root)
        dialogBinding.dialogImage.setImageResource(image)
        dialogBinding.dialogTitle.text = title
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun newsUpdater() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.newsSlider.layoutManager = layoutManager

        lifecycleScope.launch {
            try {
                val newsItems = newsApi.getNews()
                val newsDataList = newsItems.map {
                    NewsData(
                        newsTitle = it.newsTitle,
                        newsDescription = it.newsDescription,
                        newsImage = it.newsImage
                    )
                }

                val newsAdapter = NewsAdapter(newsDataList)
                binding.newsSlider.adapter = newsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.newsSlider.addOnScrollListener(object : RecyclerView.OnScrollListener() {
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
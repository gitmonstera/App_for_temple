package com.example.apptemple

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.*
import com.example.apptemple.Adapters.AnnounceAdapter
import com.example.apptemple.Adapters.NewsAdapter
import com.example.apptemple.DataClasses.AnnounceData
import com.example.apptemple.Retrofit.RetrofitClient.newsApi
import com.example.apptemple.databinding.AnnouncesDetailsFragmentBinding
import com.example.apptemple.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), AnnounceAdapter.OnItemClickListener {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var dialogBinding: AnnouncesDetailsFragmentBinding
    private var currentPosition = 0

    //Функция с конфигурацией запуска (тут менять нечего)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announceSliderAdapter()
        buttonsSlide()
        newsUpdater()
    }

    private fun announceSliderAdapter() {
        val announceRecyclerView = binding.announceSlider
        announceRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        val announceItems = listOf(
            AnnounceData("Первый анонс", R.mipmap.justannounce),
            AnnounceData("Второй анонс", R.mipmap.justannounce2),
            AnnounceData("Третий анонс", R.mipmap.justannounce3),
            AnnounceData("Четвертый анонс", R.mipmap.justannounce4),
            AnnounceData("Пятый анонс", R.mipmap.justannounce5)
        )
        announceRecyclerView.adapter = AnnounceAdapter(announceItems, this)
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(announceRecyclerView)
    }
    override fun onItemClick(announce: AnnounceData) {
        announceDialog(announce.announceImageData, announce.announceTitleData)
    }
    private fun buttonsSlide() {
        binding.forwardArrow.setOnClickListener {
            val itemCount = binding.announceSlider.adapter?.itemCount ?: 0
            if(currentPosition >= itemCount - 1) return@setOnClickListener
            currentPosition++
            binding.announceSlider.smoothScrollToPosition(currentPosition)
        }
        binding.backArrow.setOnClickListener {
            if(currentPosition <= 0) return@setOnClickListener
            currentPosition--
            binding.announceSlider.smoothScrollToPosition(currentPosition)
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
        binding.newsSlider.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch {
            try {
                val newsDataList = newsApi.getNews()
                binding.newsSlider.adapter = NewsAdapter(newsDataList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.newsSlider.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView : RecyclerView, dx : Int, dy : Int) {
                super.onScrolled(recyclerView, dx, dy)

                when {
                    dy > 0 -> {
                        binding.announceSlider.animate().translationY(-binding.announceSlider.height.toFloat())
                            .alpha(0f).setDuration(200)
                            .withEndAction { binding.announceSlider.visibility = View.GONE }.start()

                        binding.backArrow.animate().translationY(-binding.backArrow.height.toFloat())
                            .alpha(0f).setDuration(300).start()

                        binding.forwardArrow.animate().translationY(-binding.forwardArrow.height.toFloat())
                            .alpha(0f).setDuration(300).start()
                    }
                    dy < 0 -> {
                        binding.announceSlider.animate().translationY(0f).alpha(1f).setDuration(200)
                            .withStartAction { binding.announceSlider.visibility = View.VISIBLE }.start()

                        binding.backArrow.animate().translationY(0f).alpha(1f).setDuration(300).start()

                        binding.forwardArrow.animate().translationY(0f).alpha(1f).setDuration(300).start()
                    }
                }
            }
        })
    }
}
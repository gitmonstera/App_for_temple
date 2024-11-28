package com.example.apptemple

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.TextUtils
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
    private lateinit var dialogBinding: AnnouncesDetailsFragmentBinding
    private var currentPosition = 0
    private lateinit var gestureDetector: GestureDetector

    //Функция с конфигурацией запуска (тут менять нечего)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        announceSliderAdapter()
        newsUpdater()
        announceHider()
    }

    private fun announceSliderAdapter() {
        val announceRecyclerView = binding.announceSlider
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        announceRecyclerView.layoutManager = layoutManager

        val announceItems = listOf(
            AnnounceData("Ретроградный Меркурий", R.mipmap.justannounce),
            AnnounceData("За МКАДом", R.mipmap.justannounce2),
            AnnounceData("Москва", R.mipmap.justannounce3),
            AnnounceData("Пипец капец", R.mipmap.justannounce4),
            AnnounceData("Собачка :3", R.mipmap.justannounce5)
        )

        val adapter = AnnounceAdapter(announceItems, object : AnnounceAdapter.OnItemClickListener {
            override fun onItemClick(announce: AnnounceData) {
                announceDialog(announce.announceImageData, announce.announceTitleData)
            }
        })
        announceRecyclerView.adapter = adapter

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(announceRecyclerView)
    }

    /*private fun buttonsSlide() {
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
    }*/

    private fun announceDialog(image: Int, title: String) {
        val dialog = Dialog(requireContext())
        dialogBinding =
            AnnouncesDetailsFragmentBinding.inflate(LayoutInflater.from(requireContext()))
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
                /*val newsItems = newsApi.getNews()
                val newsDataList = newsItems.map {
                    NewsData(
                        newsTitle = it.newsTitle,
                        newsDescription = it.newsDescription,
                        newsImage = it.newsImage
                    )
                }*/


                val newsData = listOf(
                    NewsData(
                        "Первая новость",
                        "Эта книга адресована всем, кто изучает русский язык. Но состоит она не из правил, упражнений и учебных текстов. Для этого созданы другие замечательные учебники.\n" +
                                "\n" +
                                "У этой книги совсем иная задача. Она поможет вам научиться не только разговаривать, но и размышлять по-русски. Книга, которую вы держите в руках, составлена из афоризмов и размышлений великих мыслителей, писателей, поэтов, философов и общественных деятелей различных эпох. Их мысли - о тех вопросах, которые не перестают волновать человечество.\n" +
                                "\n" +
                                "Вы можете соглашаться или не соглашаться с тем, что прочитаете в этой книге. Возможно, вам покажется, что какие-то мысли уже устарели. Но вы должны обязательно подумать и обосновать, почему вы так считаете.\n" +
                                "\n" +
                                "А еще вы узнаете и почувствуете, как прекрасно звучат слова любви, сострадания, мудрости и доброты на русском языке.",
                        R.mipmap.justannounce5
                    ),
                    NewsData("Вторая новость", "Описание новости", R.mipmap.justannounce4),
                    NewsData("Третья новость", "Описание новости", R.mipmap.justannounce3),
                    NewsData(
                        "Четвертая новость",
                        "Вы можете соглашаться или не соглашаться с тем, что прочитаете в этой книге. Возможно, вам покажется, что какие-то мысли уже устарели. Но вы должны обязательно подумать и обосновать, почему вы так считаете.\n" +
                                "\n" +
                                "А еще вы узнаете и почувствуете, как прекрасно звучат слова любви, сострадания, мудрости и доброты на русском языке.",
                        R.mipmap.justannounce2
                    ),
                    NewsData("Пятая новость", "Описание новости", R.mipmap.justannounce),
                )

                val newsAdapter = NewsAdapter(newsData)
                binding.newsSlider.adapter = newsAdapter
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun announceHider() {
        binding.newsSlider.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                if (!recyclerView.canScrollVertically(-1)) {
                    showAnnounceSlider()
                } else if (dy > 0) {
                    hideAnnounceSlider()
                }
            }
        })
    }

    private fun hideAnnounceSlider() {
        binding.announceSlider.animate().cancel()
        binding.announceSlider.animate()
            .alpha(0F)
            .setDuration(300)
            .withEndAction {
                binding.smthg.visibility = View.GONE
                binding.announceSlider.visibility = View.GONE
            }
    }

    private fun showAnnounceSlider() {
        binding.announceSlider.animate().cancel()
        binding.announceSlider.visibility = View.VISIBLE
        binding.smthg.visibility = View.VISIBLE
        binding.announceSlider.animate()
            .alpha(1F)
            .setDuration(300)
    }
}
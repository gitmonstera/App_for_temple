package com.example.apptemple.Adapters

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apptemple.DataClasses.NewsData
import com.example.apptemple.databinding.ItemNewsBinding

class NewsAdapter(private val newsItems: List<NewsData>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isExpanded = false // Локальное состояние для каждой карточки

        fun bind(news: NewsData) {
            binding.newsTitle.text = news.newsTitle
            binding.newsDescription.text = news.newsDescription
            Glide.with(binding.newsImage.context)
                .load(news.newsImage)
                .into(binding.newsImage)

            binding.newsDescription.maxLines = 3
            binding.newsDescription.ellipsize = TextUtils.TruncateAt.END

            binding.newsDescription.post {
                val lineCount = binding.newsDescription.lineCount
                if(lineCount < 3) {
                    binding.moreInfo.visibility = View.GONE
                } else {
                    binding.moreInfo.visibility = View.VISIBLE
                }
            }

            binding.moreInfo.setOnClickListener {
                if(isExpanded) {
                    // Скрыть текст, ограничив его тремя строками
                    binding.newsDescription.maxLines = 3
                    binding.newsDescription.ellipsize = TextUtils.TruncateAt.END
                    binding.moreInfo.text = "Подробнее"
                } else {
                    // Показать полный текст
                    binding.newsDescription.maxLines = Int.MAX_VALUE
                    binding.newsDescription.ellipsize = null
                    binding.moreInfo.text = "Свернуть"
                }
                isExpanded = !isExpanded // Переключаем состояние
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsItems[position])
    }

    override fun getItemCount(): Int = newsItems.size
}
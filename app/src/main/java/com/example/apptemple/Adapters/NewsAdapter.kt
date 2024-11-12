package com.example.apptemple.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.apptemple.DataClasses.NewsData
import com.example.apptemple.R
import com.example.apptemple.databinding.NewsItemBinding

class NewsAdapter(private val newsItems: List<NewsData>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(private val binding : NewsItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsData) {
            binding.newsTitle.text = news.newsTitle
            binding.newsDescription.text = news.newsDescription
            Glide.with(binding.newsImage.context)
                .load(news.newsImage)
                .into(binding.newsImage)
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : NewsViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder : NewsViewHolder, position : Int) {
        holder.bind(newsItems[position])
    }

    override fun getItemCount() : Int = newsItems.size
}
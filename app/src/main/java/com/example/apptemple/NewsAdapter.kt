package com.example.apptemple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.DataClasses.NewsData

class NewsAdapter(private val newsItems: List<NewsData>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    class NewsViewHolder(newsView: View): RecyclerView.ViewHolder(newsView) {
        val newsTitleView: TextView = newsView.findViewById(R.id.newsTitle)
        val newsDescriptionView: TextView = newsView.findViewById(R.id.newsDescription)
        val newsImageView: ImageView = newsView.findViewById(R.id.newsImage)
    }

    override fun onCreateViewHolder(parent : ViewGroup, viewType : Int) : NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder : NewsViewHolder, position : Int) {
        val newsView = newsItems[position]
        holder.newsTitleView.text = newsView.newsTitle
        holder.newsDescriptionView.text = newsView.newsDescription
        holder.newsImageView.setImageResource(newsView.newsImage)
    }

    override fun getItemCount() : Int = newsItems.size
}
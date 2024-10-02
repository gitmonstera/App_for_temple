package com.example.apptemple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.DataClasses.AnnounceData

class AnnounceAdapter(private val announceItems: List<AnnounceData>): RecyclerView.Adapter<AnnounceAdapter.AnnounceViewHolder>() {

    class AnnounceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.announceImage)
        val titleView: TextView = itemView.findViewById(R.id.announceTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnounceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.announce_item, parent, false)
        return AnnounceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnnounceViewHolder, position: Int) {
        val announceItem = announceItems[position]
        holder.imageView.setImageResource(announceItem.announceImageData)
        holder.titleView.text = announceItem.announceTitleData
    }

    override fun getItemCount(): Int = announceItems.size
}
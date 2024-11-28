package com.example.apptemple.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.DataClasses.AnnounceData
import com.example.apptemple.R

class AnnounceAdapter(
    private val announceItems: List<AnnounceData>,
    private val listener: OnItemClickListener,
) : RecyclerView.Adapter<AnnounceAdapter.AnnounceViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(announce: AnnounceData)
    }

    class AnnounceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val announceImageView: ImageView = itemView.findViewById(R.id.announceImage)
        val announceTitleView: TextView = itemView.findViewById(R.id.announceTitle)

        fun bind(announce: AnnounceData, clickListener: OnItemClickListener) {
            announceImageView.setImageResource(announce.announceImageData)
            announceTitleView.text = announce.announceTitleData
            itemView.setOnClickListener {
                clickListener.onItemClick(announce)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnounceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.announce_item, parent, false)
        return AnnounceViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnnounceViewHolder, position: Int) {
        val announceItem = announceItems[position]
        holder.announceImageView.setImageResource(announceItem.announceImageData)
        holder.announceTitleView.text = announceItem.announceTitleData
        holder.bind(announceItem, listener)
    }

    override fun getItemCount(): Int = announceItems.size
}
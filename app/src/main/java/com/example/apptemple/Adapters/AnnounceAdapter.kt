package com.example.apptemple.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.apptemple.DataClasses.AnnounceData
import com.example.apptemple.R
import com.example.apptemple.databinding.ItemAnnounceBinding

class AnnounceAdapter(
    private val announceItems: List<AnnounceData>,
    private val listener: OnItemClickListener,
) : RecyclerView.Adapter<AnnounceAdapter.AnnounceViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(announce: AnnounceData)
    }

    class AnnounceViewHolder(private val binding: ItemAnnounceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(announce: AnnounceData, clickListener: OnItemClickListener) {
            binding.announceImage.setImageResource(announce.announceImageData)
            binding.announceTitle.text = announce.announceTitleData
            itemView.setOnClickListener {
                clickListener.onItemClick(announce)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnounceViewHolder {
        val binding = ItemAnnounceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnnounceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnnounceViewHolder, position: Int) {
        val announceItem = announceItems[position]
        holder.bind(announceItem, listener)
    }

    override fun getItemCount(): Int = announceItems.size
}
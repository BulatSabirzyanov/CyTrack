package com.example.cytrack.presentation.screens.searchfragment.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cytrack.R
import com.example.cytrack.presentation.screens.searchfragment.PlayerModel

class PlayerItemAdapter(context: Context) : ListAdapter<PlayerModel, PlayerItemAdapter.ViewHolder>(DataItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.iV_player_icon)
        private val playerName: TextView = itemView.findViewById(R.id.tV_player_name)

        fun bind(item: PlayerModel) {
            Glide.with()
                .load(R.drawable.csgo_icon)
                .into(binding.iVGameIcon)
            image = item.title
            description.text = item.description
        }
    }
}

class DataItemDiffCallback : DiffUtil.ItemCallback<DataItem>() {
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem == newItem
    }
}

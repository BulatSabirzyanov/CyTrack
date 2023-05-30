package com.example.cytrack.presentation.screens.searchfragment.adapters

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
import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel

class PlayerItemAdapter() : ListAdapter<PlayerModel, PlayerItemAdapter.ViewHolder>(PlayerItemDiffCallback()) {

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
            if (item.imageUrl!=null) {
                Glide.with(itemView.context)
                    .load(item.imageUrl)
                    .into(image)
            }else{
                Glide.with(itemView.context)
                    .load(R.drawable.baseline_person_24)
                    .into(image)
            }
            playerName.text = item.name
        }
    }
}

class PlayerItemDiffCallback : DiffUtil.ItemCallback<PlayerModel>() {
    override fun areItemsTheSame(oldItem: PlayerModel, newItem: PlayerModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PlayerModel, newItem: PlayerModel): Boolean {
        return oldItem == newItem
    }
}

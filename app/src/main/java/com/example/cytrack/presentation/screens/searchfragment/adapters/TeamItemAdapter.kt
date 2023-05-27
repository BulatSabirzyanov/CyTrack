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
import com.example.cytrack.presentation.screens.searchfragment.PlayerModel
import com.example.cytrack.presentation.screens.searchfragment.TeamModel

class TeamItemAdapter() : ListAdapter<TeamModel, TeamItemAdapter.ViewHolder>(TeamItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.team_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.iV_team_icon)
        private val playerName: TextView = itemView.findViewById(R.id.tV_team_name)

        fun bind(item: TeamModel) {
            Glide.with(itemView.context)
                .load(item.imageUrl)
                .into(image)

            playerName.text = item.name
        }
    }
}

class TeamItemDiffCallback : DiffUtil.ItemCallback<TeamModel>() {
    override fun areItemsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TeamModel, newItem: TeamModel): Boolean {
        return oldItem == newItem
    }
}

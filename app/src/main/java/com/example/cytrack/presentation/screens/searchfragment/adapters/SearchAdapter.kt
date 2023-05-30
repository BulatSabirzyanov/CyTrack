package com.example.cytrack.presentation.screens.searchfragment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cytrack.R
import com.example.cytrack.databinding.HeadingItemBinding
import com.example.cytrack.databinding.PlayerLayoutSearchRvBinding
import com.example.cytrack.databinding.TeamLayoutSearchRvBinding
import com.example.cytrack.presentation.screens.searchfragment.models.HeadingModel
import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel
import com.example.cytrack.presentation.screens.searchfragment.models.TeamModel

class SearchAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(SearchDiffCallback()) {

    companion object {
        private const val TYPE_PLAYER = 0
        private const val TYPE_TEAM = 1
        private const val TYPE_HEADING = 2
    }

    private var listener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_PLAYER -> {
                val binding = PlayerLayoutSearchRvBinding.inflate(inflater, parent, false)
                PlayerViewHolder(binding)
            }
            TYPE_TEAM -> {
                val binding = TeamLayoutSearchRvBinding.inflate(inflater, parent, false)
                TeamViewHolder(binding)
            }
            TYPE_HEADING -> {
                val binding = HeadingItemBinding.inflate(inflater, parent, false)
                HeadingItemViewHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is PlayerViewHolder -> holder.bind(item as PlayerModel)
            is TeamViewHolder -> holder.bind(item as TeamModel)
            is HeadingItemViewHolder -> holder.bind(item as HeadingModel)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when (item) {
            is PlayerModel -> TYPE_PLAYER
            is TeamModel -> TYPE_TEAM
            is HeadingModel -> TYPE_HEADING
            else -> throw IllegalArgumentException("Invalid item type")
        }
    }

    inner class PlayerViewHolder(private var binding: PlayerLayoutSearchRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener?.onItemClick(item)
                }
            }
        }

        fun bind(item: PlayerModel) {
            with(binding) {
                if (item.imageUrl != null) {
                    Glide.with(itemView.context)
                        .load(item.imageUrl)
                        .into(iVPlayerIconSearchRv)
                } else {
                    Glide.with(itemView.context)
                        .load(R.drawable.baseline_person_24)
                        .into(iVPlayerIconSearchRv)
                }
                tVPlayerNameSearchRv.text = item.name
                tVPlayerFirstNameSearchRv.text = item.firstName
                tVPlayerSecondNameSearchRv.text = item.lastName
            }
        }
    }

    inner class TeamViewHolder(private var binding: TeamLayoutSearchRvBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    listener?.onItemClick(item)
                }
            }
        }

        fun bind(item: TeamModel) {
            with(binding) {
                if (item.imageUrl != null) {
                    Glide.with(itemView.context)
                        .load(item.imageUrl)
                        .into(iVTeamIconSearchRv)
                } else {
                    Glide.with(itemView.context)
                        .load(R.drawable.baseline_peoples_24)
                        .into(iVTeamIconSearchRv)
                }
                tVTeamNameSearchRv.text = item.name
            }
        }
    }

    inner class HeadingItemViewHolder(private var binding: HeadingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HeadingModel) {
            with(binding) {
                root.text = root.context.getString(item.text)
            }
        }
    }
}

class SearchDiffCallback : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        return when {
            oldItem is PlayerModel && newItem is PlayerModel -> oldItem.id == newItem.id
            oldItem is TeamModel && newItem is TeamModel -> oldItem.id == newItem.id
            oldItem is HeadingModel && newItem is HeadingModel -> oldItem.text == newItem.text
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        return oldItem == newItem
    }
}

interface OnItemClickListener {
    fun onItemClick(item: Any)
}

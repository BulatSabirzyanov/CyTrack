package com.example.cytrack.presentation.schedulefragment.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.cytrack.databinding.GameItemBinding
import com.example.cytrack.presentation.schedulefragment.AdapterDelegate
import com.example.cytrack.presentation.schedulefragment.DelegateItem


class GameDelegate(private val glide: RequestManager) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = ViewHolder(
        GameItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int
    ) {

        (holder as ViewHolder).bind(item.content() as GameModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is GameDelegateItem

    inner class ViewHolder(private val binding: GameItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: GameModel) {
            with(binding) {
                tVDate.text = model.date
                tVTeam1.text = model.team1
                tVTeam2.text = model.team2
                glide.load(model.team1Icon).into(iVTeam1Icon)
                glide.load(model.team2Icon).into(iVTeam2Icon)
            }
        }
    }

}
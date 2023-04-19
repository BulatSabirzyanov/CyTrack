package com.example.cytrack.presentation.schedulefragment.tournament

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cytrack.databinding.TournamentItemBinding
import com.example.cytrack.presentation.schedulefragment.AdapterDelegate
import com.example.cytrack.presentation.schedulefragment.DelegateItem


class TournamentDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(
            TournamentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ViewHolder).bind(item.content() as TournamentModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is TournamentDelegateItem

    inner class ViewHolder(private val binding: TournamentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: TournamentModel) {
            with(binding) {
                tVTournamentName.text = model.name

            }
        }
    }
}
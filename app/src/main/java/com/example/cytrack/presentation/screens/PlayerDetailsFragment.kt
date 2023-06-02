package com.example.cytrack.presentation.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.cytrack.R
import com.example.cytrack.databinding.FragmentPlayerDetailsBinding

import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel
import java.util.Locale

class PlayerDetailsFragment : Fragment(R.layout.fragment_player_details) {
    private lateinit var binding: FragmentPlayerDetailsBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val player = arguments?.getParcelable<PlayerModel>("player",PlayerModel::class.java)

        player?.let { fillPlayerDetails(it) }
    }

    private fun fillPlayerDetails(player: PlayerModel) {

        with(binding) {


            if (player.imageUrl != null) {
                Glide.with(requireContext())
                    .load(player.imageUrl)
                    .into(iVPlayerIconSearchRv)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.baseline_person_24)
                    .into(iVPlayerIconSearchRv)
            }
            val flagUrl =
                "https://flagcdn.com/64x48/" + player.nationality?.lowercase(Locale.ENGLISH) + ".png"
            if (player.nationality != null) {
                Glide.with(requireContext())
                    .load(flagUrl)
                    .into(imageViewNationality)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.baseline_peoples_24)
                    .into(imageViewNationality)
            }
            tVPlayerNameSearchRv.text = player.name
            tVPlayerFirstNameSearchRv.text = player.firstName
            tVPlayerSecondNameSearchRv.text = player.lastName
            tVTeamName.text = player.currentTeam
            tVNationality.text = player.nationality

        }
    }
}


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
import com.example.cytrack.databinding.FragmentTeamPlayerDetailsBinding
import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel
import com.example.cytrack.presentation.screens.searchfragment.models.TeamPlayerModel
import java.util.Locale

class TeamPlayerDetailsFragment : Fragment(R.layout.fragment_team_player_details) {
    private lateinit var binding: FragmentTeamPlayerDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeamPlayerDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val player =
            arguments?.getParcelable<TeamPlayerModel>("Player", TeamPlayerModel::class.java)

        player?.let { fillPlayerDetails(it) }
    }

    private fun fillPlayerDetails(player: TeamPlayerModel) {

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
            tVAge.text = player.age.toString()
            tVBirthday.text = player.birthday
            tVSlug.text = player.slug
            tVNationality.text = player.nationality


        }
    }
}

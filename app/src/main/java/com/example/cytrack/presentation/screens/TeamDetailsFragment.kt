package com.example.cytrack.presentation.screens

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cytrack.OnItemClickListener
import com.example.cytrack.R
import com.example.cytrack.databinding.FragmentTeamDetailsBinding
import com.example.cytrack.presentation.screens.searchfragment.adapters.SearchAdapter
import com.example.cytrack.presentation.screens.searchfragment.models.TeamModel
import com.example.cytrack.presentation.screens.searchfragment.models.TeamPlayerModel
import java.util.Locale


class TeamDetailsFragment : Fragment(R.layout.fragment_team_details), OnItemClickListener {
    private lateinit var binding: FragmentTeamDetailsBinding


    private lateinit var adapter: SearchAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentTeamDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {


            val team = arguments?.getParcelable<TeamModel>("team", TeamModel::class.java)
            if (team != null) {
                if (team.imageUrl != null) {
                    Glide.with(requireContext())
                        .load(team.imageUrl)
                        .into(imageViewTeamIcon)
                } else {
                    Glide.with(requireContext())
                        .load(R.drawable.baseline_peoples_24)
                        .into(imageViewTeamIcon)
                }
                textViewTeamName.text = team.name

                textViewTeamLocation.text = team.location
                textViewTeamAcronym.text = team.acronym
                val flagUrl =
                    "https://flagcdn.com/64x48/" + team.location?.lowercase(Locale.ENGLISH) + ".png"
                if (team.location != null) {
                    Glide.with(requireContext())
                        .load(flagUrl)
                        .into(imageViewLocation)
                } else {
                    Glide.with(requireContext())
                        .load(R.drawable.baseline_peoples_24)
                        .into(imageViewLocation)
                }

            }
            adapter = SearchAdapter(this@TeamDetailsFragment)
            recyclerViewPlayers.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewPlayers.adapter = adapter
            team?.players?.let { players ->
                adapter.submitList(players)
            }
        }

    }

    override fun onItemClick(item: Any) {
        val bundle = Bundle()
        // Ваша логика обработки нажатий
        if (item is TeamPlayerModel) {

            bundle.putParcelable("Player", item)
            findNavController().navigate(
                R.id.action_teamDetailsFragment_to_teamPlayerDetailsFragment,
                bundle
            )
        }
    }

}

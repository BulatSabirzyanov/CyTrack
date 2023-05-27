package com.example.cytrack.presentation.screens.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cytrack.R
import com.example.cytrack.databinding.FragmentScheduleBinding
import com.example.cytrack.databinding.FragmentSearchBinding
import com.example.cytrack.di.appComponent
import com.example.cytrack.di.lazyViewModel
import com.example.cytrack.presentation.screens.schedulefragment.MainAdapter
import com.example.cytrack.presentation.screens.schedulefragment.ScheduleFragment
import com.example.cytrack.presentation.screens.searchfragment.adapters.PlayerItemAdapter
import com.example.cytrack.presentation.screens.searchfragment.adapters.TeamItemAdapter
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel
import com.example.cytrack.presentation.viewmodel.SearchFragmentViewModel


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private var game: String? = null
    private var adapterPlayer = PlayerItemAdapter()
    private var adapterTeam = TeamItemAdapter()
    private val viewModel: SearchFragmentViewModel by lazyViewModel {
        requireContext().appComponent().searchFragmentViewModel()
            .create(game = game ?: "")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = arguments?.getString(ScheduleFragment.ARG_GAME) ?: ""



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        binding.recyclerViewPlayers.adapter = adapterPlayer
        binding.recyclerViewTeams.adapter = adapterTeam
    }

    private fun observeData() {

        viewModel.listOfPlayers.observe(viewLifecycleOwner) { playerModels ->
            adapterPlayer.submitList(playerModels)
        }
        viewModel.listOfTeams.observe(viewLifecycleOwner) { teamModels ->
            adapterTeam.submitList(teamModels)
        }
    }



    companion object {
        private const val ARG_GAME = "game"

        fun newInstance(game: String) = ScheduleFragment().apply {
            arguments = Bundle().apply { putString(ARG_GAME, game) }
        }
    }
}

package com.example.cytrack.presentation.schedulefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cytrack.R
import com.example.cytrack.databinding.FragmentScheduleBinding
import com.example.cytrack.di.appComponent
import com.example.cytrack.di.lazyViewModel
import com.example.cytrack.presentation.schedulefragment.game.GameDelegate
import com.example.cytrack.presentation.schedulefragment.game.GameModel
import com.example.cytrack.presentation.schedulefragment.tournament.TournamentDelegate
import com.example.cytrack.presentation.schedulefragment.tournament.TournamentModel
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel


class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private lateinit var binding: FragmentScheduleBinding
    private var game: String? = null
    private val adapter = MainAdapter()
    private val viewModel: ScheduleFragmentViewModel by lazyViewModel {
        requireContext().appComponent().scheduleFragmentViewModel()
            .create(game = game ?: "")
    }
    private var stubGameList: List<GameModel> = emptyList()
    private var stubTournamentList: List<TournamentModel> = emptyList()

    private fun observeData() {

        viewModel.listOfGames.observe(viewLifecycleOwner) { gameModels ->
            stubGameList = gameModels
            updateAdapterData()
        }
        viewModel.listOfTournaments.observe(viewLifecycleOwner) { tournamentModels ->
            stubTournamentList = tournamentModels
            updateAdapterData()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        adapter.apply {
            addDelegate(GameDelegate(Glide.with(requireContext())))
            addDelegate(TournamentDelegate())
        }
        binding.recycler.adapter = adapter
        adapter.submitList(stubGameList.concatenateWithTournament(stubTournamentList))
    }

    private fun updateAdapterData() {
        if (stubGameList.isNotEmpty() && stubTournamentList.isNotEmpty()) {
            adapter.submitList(stubGameList.concatenateWithTournament(stubTournamentList))
        }
    }

    companion object {
        fun newInstance() = ScheduleFragment()
    }
}

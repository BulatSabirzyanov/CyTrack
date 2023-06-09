package com.example.cytrack.presentation.screens.schedulefragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.example.cytrack.R
import com.example.cytrack.databinding.FragmentScheduleBinding
import com.example.cytrack.di.appComponent
import com.example.cytrack.di.lazyViewModel
import com.example.cytrack.presentation.screens.schedulefragment.delegeteadapter.MainAdapter
import com.example.cytrack.presentation.screens.schedulefragment.delegeteadapter.concatenateWithTournament
import com.example.cytrack.presentation.screens.schedulefragment.game.GameDelegate
import com.example.cytrack.presentation.screens.schedulefragment.game.GameModel
import com.example.cytrack.presentation.screens.schedulefragment.tournament.TournamentDelegate
import com.example.cytrack.presentation.screens.schedulefragment.tournament.TournamentModel
import com.example.cytrack.presentation.screens.searchfragment.adapters.HorizontalSpaceItemDecoration
import com.example.cytrack.presentation.screens.searchfragment.adapters.VerticalSpaceItemDecoration
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel


class ScheduleFragment : Fragment(R.layout.fragment_schedule) {

    private lateinit var binding: FragmentScheduleBinding
    private lateinit var game: String
    private val adapter = MainAdapter()
    private val viewModel: ScheduleFragmentViewModel by lazyViewModel {
        requireContext().appComponent().scheduleFragmentViewModel()
            .create(game = game)
    }
    private var stubGameList: List<GameModel> = emptyList()
    private var stubTournamentList: List<TournamentModel> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = arguments?.getString(ARG_GAME) ?: ""
    }


    private fun setRecyclerViewScrollListener(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (!viewModel.isLoading && totalItemCount <= (lastVisibleItem + 10)) {
                        viewModel.getSchedule(game = game)
                    }
                }
            }
        })
    }

    private fun observeData() {

        viewModel.listOfGames.observe(viewLifecycleOwner) { gameModels ->
            stubGameList = gameModels
            updateAdapterData()
        }
        viewModel.listOfTournaments.observe(viewLifecycleOwner) { tournamentModels ->
            stubTournamentList = tournamentModels
            updateAdapterData()
        }
        viewModel.progressBarState.observe(viewLifecycleOwner) { isVisible ->
            binding.progressBar.isVisible = isVisible
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
        with(binding){


            observeData()
            adapter.apply {
                addDelegate(GameDelegate(Glide.with(requireContext())))
                addDelegate(TournamentDelegate())
            }
            val spacingDp = 10f
            val itemVerticalDecoration = VerticalSpaceItemDecoration(spacingDp)
            recycler.adapter = adapter
            recycler.addItemDecoration(itemVerticalDecoration)
            adapter.submitList(stubGameList.concatenateWithTournament(stubTournamentList))
            setRecyclerViewScrollListener(recycler)

            when(game){
                "csgo" -> {
                    Glide.with(requireContext()).load(R.drawable.csgo).into(iVGameIcon)
                }
                "dota2" -> {
                    Glide.with(requireContext()).load(R.drawable.dota2_icon).into(iVGameIcon)
                }
                "valorant" -> {
                    Glide.with(requireContext()).load(R.drawable.valorant_icon).into(iVGameIcon)
                }

                else -> {

                }
            }

        }



    }

    private fun updateAdapterData() {
        if (stubGameList.isNotEmpty() && stubTournamentList.isNotEmpty()) {
            adapter.submitList(stubGameList.concatenateWithTournament(stubTournamentList))
        }
    }

    companion object {
        const val ARG_GAME = "game"

        fun newInstance(game: String) = ScheduleFragment().apply {
            arguments = Bundle().apply { putString(ARG_GAME, game) }
        }
    }
}

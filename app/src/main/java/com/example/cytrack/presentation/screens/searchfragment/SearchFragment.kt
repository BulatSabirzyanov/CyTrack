package com.example.cytrack.presentation.screens.searchfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup


class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var binding: FragmentSearchBinding
    private var game: String = "csgo"
    private var name: String? = null
    private var adapterPlayer = PlayerItemAdapter()
    private var adapterTeam = TeamItemAdapter()
    private val viewModel: SearchFragmentViewModel by lazyViewModel {
        requireContext().appComponent().searchFragmentViewModel()
            .create(game = game ?: "", name = name)
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

    private fun setRecyclerViewPlayersScrollListener(selectedGame: String) {
        binding.recyclerViewPlayers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (!viewModel.isPlayerLoading && totalItemCount <= (lastVisibleItem + 10)) {
                        viewModel.getPlayersData(game = game)
                    }
                }
            }
        })
    }

    private fun setRecyclerViewTeamsScrollListener(selectedGame: String) {
        binding.recyclerViewTeams.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dx > 0) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val totalItemCount = layoutManager.itemCount
                    val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                    if (!viewModel.isTeamLoading && totalItemCount <= (lastVisibleItem + 10)) {
                        viewModel.getTeamsData(game = game)
                    }
                }
            }
        })
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){
            observeData()
            recyclerViewPlayers.adapter = adapterPlayer
            recyclerViewTeams.adapter = adapterTeam
            setRecyclerViewPlayersScrollListener(game)
            setRecyclerViewTeamsScrollListener(game)
            addChipButton.setOnClickListener {
                val chipGroup = chipGroup

                // Проверяем, что количество чипов не превышает 6
                if (chipGroup.childCount < 7) {
                    val chip = Chip(requireContext())
                    chip.text = "Your text here" // Замените это на текст, который вы хотите добавить
                    chip.isCloseIconVisible = true
                    chip.setOnCloseIconClickListener {
                        chipGroup.removeView(chip) // Обработка удаления чипа
                    }
                    chipGroup.addView(chip)
                } else {
                    Toast.makeText(requireContext(), "Maximum number of chips reached", Toast.LENGTH_SHORT).show()
                }
            }

            csgoChip.setOnClickListener {
                if (csgoChip.isChecked) {
                    val selectedGame = "csgo"
                    viewModel.listOfPlayers.value?.clear()
                    viewModel.listOfTeams.value?.clear()
                    setRecyclerViewPlayersScrollListener(selectedGame)
                    setRecyclerViewTeamsScrollListener(selectedGame)
                }
            }
            dota2Chip.setOnClickListener {
                if (dota2Chip.isChecked) {
                    val selectedGame = "dota2"
                    viewModel.listOfPlayers.value?.clear()
                    viewModel.listOfTeams.value?.clear()
                    setRecyclerViewPlayersScrollListener(selectedGame)
                    setRecyclerViewTeamsScrollListener(selectedGame)
                }
            }
        }



    }


    private fun observeData() {

        viewModel.listOfPlayers.observe(viewLifecycleOwner) { playerModels ->
            adapterPlayer.submitList(playerModels)
            adapterPlayer.notifyDataSetChanged()
        }

        viewModel.listOfTeams.observe(viewLifecycleOwner) { teamModels ->
            adapterTeam.submitList(teamModels)
            adapterTeam.notifyDataSetChanged()
        }
        viewModel.listOfPlayersSearchForm.observe(viewLifecycleOwner){
            playerModels->adapterPlayer.submitList(playerModels)
        }
    }



    companion object {
        private const val ARG_GAME = "game"

        fun newInstance(game: String) = ScheduleFragment().apply {
            arguments = Bundle().apply { putString(ARG_GAME, game) }
        }
    }
}

private fun <E> List<E>?.clear() {
    (this as? MutableList<E>)?.clear()
}

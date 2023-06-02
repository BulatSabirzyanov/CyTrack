package com.example.cytrack.presentation.screens.searchfragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cytrack.OnItemClickListener
import com.example.cytrack.R
import com.example.cytrack.databinding.FragmentSearchBinding
import com.example.cytrack.di.appComponent
import com.example.cytrack.di.lazyViewModel
import com.example.cytrack.presentation.screens.schedulefragment.ScheduleFragment
import com.example.cytrack.presentation.screens.searchfragment.adapters.PlayerItemAdapter
import com.example.cytrack.presentation.screens.searchfragment.adapters.SearchAdapter
import com.example.cytrack.presentation.screens.searchfragment.adapters.TeamItemAdapter
import com.example.cytrack.presentation.screens.searchfragment.models.PlayerModel
import com.example.cytrack.presentation.screens.searchfragment.models.TeamModel
import com.example.cytrack.presentation.viewmodel.SearchFragmentViewModel


class SearchFragment : Fragment(R.layout.fragment_search), OnItemClickListener {

    private lateinit var binding: FragmentSearchBinding
    private var game: String = "csgo"
    private var selectedGame = ""

    private var name: String? = null
    private lateinit var playerAdapter: PlayerItemAdapter
    private lateinit var teamAdapter: TeamItemAdapter
    private lateinit var searchAdapter: SearchAdapter
    private val viewModel: SearchFragmentViewModel by lazyViewModel {
        requireContext().appComponent().searchFragmentViewModel().create(game = game, name = name)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = arguments?.getString(ScheduleFragment.ARG_GAME) ?: ""
        playerAdapter = PlayerItemAdapter(this@SearchFragment)
        teamAdapter = TeamItemAdapter(this@SearchFragment)
        searchAdapter = SearchAdapter(this@SearchFragment)
    }

    private fun setRecyclerViewPlayersScrollListener() {
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

    private fun setRecyclerViewTeamsScrollListener() {
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
        with(binding) {
            var searchQuery = ""
            observeData()

            recyclerViewPlayers.adapter = playerAdapter



            recyclerViewTeams.adapter = teamAdapter



            searchRV.adapter = searchAdapter

            setRecyclerViewPlayersScrollListener()
            setRecyclerViewTeamsScrollListener()

            if (searchQuery.isNotEmpty()) {
                // Восстановить ранее полученные данные без повторных запросов
                if (selectedGame.isNotEmpty()) {
                    viewModel.getInfoSearchForm(selectedGame, searchQuery)
                } else {
                    viewModel.getInfoSearchForm("", searchQuery)
                    viewModel.getTeamsData("")
                    viewModel.getPlayersData("")
                }
            }




            csgoChip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    searchQuery = editText.text.toString().trim()
                    selectedGame = "csgo"
                    viewModel.clearLists()
                    viewModel.getPlayersData(selectedGame)
                    viewModel.getTeamsData(selectedGame)
                    if (searchQuery.isNotEmpty()) viewModel.getInfoSearchForm(
                        selectedGame,
                        searchQuery
                    )
                    viewModel.currentPlayersPage = 1
                    viewModel.currentTeamsPage = 1
                } else {
                    selectedGame = ""
                    viewModel.clearLists()
                }
            }
            dota2Chip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    searchQuery = editText.text.toString().trim()
                    selectedGame = "dota2"
                    viewModel.clearLists()
                    viewModel.getPlayersData(selectedGame)
                    viewModel.getTeamsData(selectedGame)
                    if (searchQuery.isNotEmpty()) viewModel.getInfoSearchForm(
                        selectedGame,
                        searchQuery
                    )
                    viewModel.currentPlayersPage = 1
                    viewModel.currentTeamsPage = 1
                } else {
                    selectedGame = ""
                    viewModel.clearLists()

                }

            }
            valorantChip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    searchQuery = editText.text.toString().trim()
                    selectedGame = "valorant"
                    viewModel.clearLists()
                    viewModel.getPlayersData(selectedGame)
                    viewModel.getTeamsData(selectedGame)
                    if (searchQuery.isNotEmpty()) viewModel.getInfoSearchForm(
                        selectedGame,
                        searchQuery
                    )
                    viewModel.currentPlayersPage = 1
                    viewModel.currentTeamsPage = 1
                } else {
                    selectedGame = ""
                    viewModel.clearLists()
                }

            }

            lolChip.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    searchQuery = editText.text.toString().trim()
                    selectedGame = "lol"
                    viewModel.clearLists()
                    viewModel.getPlayersData(selectedGame)
                    viewModel.getTeamsData(selectedGame)
                    if (searchQuery.isNotEmpty()) viewModel.getInfoSearchForm(
                        selectedGame,
                        searchQuery
                    )
                    viewModel.currentPlayersPage = 1
                    viewModel.currentTeamsPage = 1
                } else {
                    selectedGame = ""
                    viewModel.clearLists()
                }

            }

            icon.setOnClickListener {
                searchQuery = editText.text.toString().trim()
                if (searchQuery.isNotEmpty()) {
                    if (selectedGame.isNotEmpty())
                        viewModel.getInfoSearchForm(selectedGame, searchQuery
                    )
                    else {
                        viewModel.getInfoSearchForm("", searchQuery)
                        viewModel.getTeamsData("")
                        viewModel.getPlayersData("")
                    }
                } else {
                    Toast.makeText(requireContext(), "Enter a search query", Toast.LENGTH_SHORT)
                        .show()
                }
                val inputMethodManager =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)


            }

        }


    }


    private fun observeData() {

        viewModel.listOfPlayers.observe(viewLifecycleOwner) { playerModels ->
            playerAdapter.submitList(playerModels)

        }

        viewModel.listOfTeams.observe(viewLifecycleOwner) { teamModels ->
            teamAdapter.submitList(teamModels)

        }
        viewModel.listOfPlayersSearchForm.observe(viewLifecycleOwner) { listOfPlayersSearchForm ->
            searchAdapter.submitList(listOfPlayersSearchForm)

        }


    }

    override fun onItemClick(item: Any) {
        val bundle = Bundle()


        if (item is PlayerModel) {

            bundle.putParcelable("player", item)
            findNavController().navigate(
                R.id.action_searchFragment_to_playerDetailsFragment, bundle
            )
        } else if (item is TeamModel) {

            bundle.putParcelable("team", item)
            findNavController().navigate(
                R.id.action_searchFragment_to_teamDetailsFragment, bundle
            )

        }
    }


    companion object {
        private const val ARG_GAME = "game"


    }
}



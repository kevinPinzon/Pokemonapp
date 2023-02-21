package com.example.pokemonapp.ui.team

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.snapshots.Snapshot.Companion.observe
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.Pokedex
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.databinding.FragmentTeamsBinding
import com.example.pokemonapp.ui.components.adapter.TeamListAdapter
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsFragment : Fragment(), ClickListenerTeam {

    private var _binding: FragmentTeamsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamsViewModel by viewModels()
    private var teamListAdapter: TeamListAdapter?= null

    lateinit var pref: SharedPreferences
    private lateinit var userID: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = requireActivity().getSharedPreferences("MyPref", 0)
        userID = pref.getString("userId", "").toString()

        initObservers()
        initListeners()

        teamListAdapter = TeamListAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = teamListAdapter

        viewModel.getTeams(userID)
    }

    private fun initObservers() {
        viewModel.teamsState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    binding.progress.visibility = View.GONE
                    binding.emptyImg.visibility = View.GONE
                    binding.tvName.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                    teamListAdapter?.setItems(list = state.data)
                }
                is Resource.Error -> {
                    binding.recyclerview.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    binding.emptyImg.visibility = View.VISIBLE
                    binding.tvName.visibility = View.VISIBLE
                }
                is Resource.Loading ->{
                    binding.emptyImg.visibility = View.GONE
                    binding.tvName.visibility = View.GONE
                    binding.recyclerview.visibility = View.VISIBLE
                    binding.progress.visibility = View.VISIBLE
                }
                else -> {
                    binding.recyclerview.visibility = View.GONE
                    binding.progress.visibility = View.GONE
                    binding.emptyImg.visibility = View.VISIBLE
                    binding.tvName.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initListeners() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun teamSelect(data: Team) {
        val bundle = bundleOf("teamName" to data.name)
//        findNavController().navigate(
//            R.id.action_navigation_pokedex_to_navigation_pokemons,
//            bundle)
    }
}

interface ClickListenerTeam {
    fun teamSelect(data: Team)
}
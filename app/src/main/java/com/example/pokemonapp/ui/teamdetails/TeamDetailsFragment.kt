package com.example.pokemonapp.ui.teamdetails

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.databinding.FragmentTeamdetailsBinding
import com.example.pokemonapp.ui.components.adapter.PokemonDetailsAdapter
import com.example.pokemonapp.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailsFragment : Fragment() {

    private var _binding: FragmentTeamdetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamDetailsViewModel by viewModels()
    private var pokemonDetailsAdapter: PokemonDetailsAdapter? = null

    private var team = Team()
    private lateinit var pref: SharedPreferences
    private lateinit var userID: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamdetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        team = arguments?.getSerializable("teamData") as Team
        pref = requireActivity().getSharedPreferences("MyPref", 0)
        userID = pref.getString("userId", "").toString()

        pokemonDetailsAdapter = PokemonDetailsAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = pokemonDetailsAdapter

        pokemonDetailsAdapter?.setItems(list = team.pokemons)
        binding.tvTeamName.text = team.name
        binding.tvTeamDescription.text =
            if(team.pokemons[0].pokedexDescription.isBlank())
                team.pokemons[0].pokedexDescription else
                    "Without pokedex description."

        initListeners()
        initObserver()
    }

    private fun initObserver() {
        viewModel.deleteTeamState.observe(viewLifecycleOwner) { state ->
            when(state) {
                is Resource.Success -> {
                    binding.progress.isVisible = false
                    Snackbar.make(requireView(), "Pokémon team successfully eliminated.", Snackbar.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
                is Resource.Error -> {
                    binding.progress.isVisible = false
                    Snackbar.make(requireView(), "Error, pokemon team has not been removed.", Snackbar.LENGTH_SHORT).show()
                    activity?.onBackPressed()
                }
                is Resource.Loading -> binding.progress.isVisible = true
                else -> Unit
            }
        }
    }

    private fun initListeners() {
        binding.fabDeleteTeam.setOnClickListener {
            viewModel.deleteTeam(team, userID)
            Snackbar.make(requireView(), "Pokemon team removed successfully.", Snackbar.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
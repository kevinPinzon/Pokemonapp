package com.example.pokemonapp.ui.teamdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.databinding.FragmentTeamdetailsBinding
import com.example.pokemonapp.ui.components.adapter.PokemonDetailsAdapter
import com.example.pokemonapp.ui.team.TeamsViewModel

class TeamDetailsFragment : Fragment() {

    private var _binding: FragmentTeamdetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TeamsViewModel by viewModels()
    private var pokemonDetailsAdapter: PokemonDetailsAdapter? = null

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

        var team = arguments?.getSerializable("teamData") as Team

        pokemonDetailsAdapter = PokemonDetailsAdapter()
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = pokemonDetailsAdapter

        pokemonDetailsAdapter?.setItems(list = team.pokemons)
        binding.tvTeamName.text = team.name
        binding.tvTeamDescription.text =
            if(team.pokemons[0].pokedexDescription.isBlank())
                team.pokemons[0].pokedexDescription else
                    "Without pokedex description."
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
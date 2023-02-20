package com.example.pokemonapp.ui.pokemonlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.data.model.PokemonList
import com.example.pokemonapp.databinding.FragmentPokemonlistBinding
import com.example.pokemonapp.ui.adapter.PokemonListAdapter

class PokemonlistFragment : Fragment(), ClickListenerPokemon{

    private var _binding: FragmentPokemonlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokemonlistViewModel by viewModels()
    private var pokemonListAdapter: PokemonListAdapter?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokemonlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokemonListAdapter = PokemonListAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = pokemonListAdapter

        initObservers()
        initListeners()
        viewModel.getPokemonByPokedex(arguments?.getString("pokedexName").toString())

    }

    private fun initListeners() {

    }

    private fun initObservers() {
        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }
        viewModel.pokemonLiveList.observe(viewLifecycleOwner) {
            pokemonListAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }
        viewModel.pokedexName.observe(viewLifecycleOwner) {
            binding.tvName.text = it
        }
        viewModel.pokedexDescription.observe(viewLifecycleOwner) {
            binding.tvDescription.text = "Description: $it"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun pokemonSelect(data: PokemonList) {

    }
}
interface ClickListenerPokemon {
    fun pokemonSelect(data: PokemonList)
}
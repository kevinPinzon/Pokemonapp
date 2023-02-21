package com.example.pokemonapp.ui.pokemonlist

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonList
import com.example.pokemonapp.data.model.PokemonSpecie
import com.example.pokemonapp.databinding.FragmentPokemonlistBinding
import com.example.pokemonapp.ui.adapter.PokemonListAdapter
import com.google.android.material.snackbar.Snackbar

class PokemonlistFragment : Fragment(), ClickListenerPokemon{

    private var _binding: FragmentPokemonlistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokemonlistViewModel by viewModels()
    private var pokemonListAdapter: PokemonListAdapter?= null

    var pokemonSelected: ArrayList<PokemonSpecie> = ArrayList()

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
        binding.fabAddteam.setOnClickListener {
            when {
                pokemonSelected.size < 3 -> {
                    Snackbar.make(requireView(), "Debe seleccionar al menos 3 pokemon.", Snackbar.LENGTH_LONG).show()
                }
                pokemonSelected.size > 6 -> {
                    Snackbar.make(requireView(), "No puede seleccionar mas de 6 pokemon.", Snackbar.LENGTH_LONG).show()
                }
                else -> {
                    Snackbar.make(it, "Se abre formulario", Snackbar.LENGTH_LONG).show()
                }
            }
        }
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

    override fun pokemonSelect(pokemon: PokemonList) {
        if (!pokemon.pokemonSpecie.selected) {
            pokemon.pokemonSpecie.selected = true
            pokemonSelected.add(pokemon.pokemonSpecie)
        } else {
            pokemonSelected.forEach { element ->
            if (element == pokemon.pokemonSpecie) {
                pokemonSelected.remove(element)
                pokemon.pokemonSpecie.selected = false
                return
                }
            }
        }
    }

}

interface ClickListenerPokemon {
    fun pokemonSelect(data: PokemonList)
}
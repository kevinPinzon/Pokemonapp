package com.example.pokemonapp.ui.pokedexlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.Pokedex
import com.example.pokemonapp.databinding.FragmentPokedexBinding
import com.example.pokemonapp.ui.components.adapter.PokedexAdapter

class PokedexlistFragment : Fragment(), ClickListenerPokedex {

    private var _binding: FragmentPokedexBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PokedexlistViewModel by viewModels()
    private var pokedexAdapter: PokedexAdapter?= null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokedexAdapter = PokedexAdapter(this)
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = pokedexAdapter

        initObservers()
        initListeners()
        viewModel.getPokedexes(arguments?.getString("regionName").toString())

        println("regionName loaded: ${arguments?.getString("regionName")}")
    }

    private fun initListeners() {
    }

    private fun initObservers() {
        viewModel.progressState.observe(viewLifecycleOwner) { show ->
            binding.progress.isVisible = show
        }

        viewModel.pokedexLiveList.observe(viewLifecycleOwner) {
            println("pokedexes: $it")
            pokedexAdapter?.setItems(list = it)
            binding.progress.isInvisible = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun pokedexSelect(data: Pokedex) {
        val bundle = bundleOf("pokedexName" to data.name)
        findNavController().navigate(
            R.id.action_navigation_pokedex_to_navigation_pokemons,
            bundle)
    }

}

interface ClickListenerPokedex {
    fun pokedexSelect(data: Pokedex)
}
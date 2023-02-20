package com.example.pokemonapp.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.model.PokemonList

import com.example.pokemonapp.databinding.PokemonRowBinding

class PokemonListViewHolder(binding: PokemonRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: PokemonRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: PokemonList) {
        binding?.let { view->
            view.pokemonName = model.pokemonSpecie.pokemonName
        }
    }
}
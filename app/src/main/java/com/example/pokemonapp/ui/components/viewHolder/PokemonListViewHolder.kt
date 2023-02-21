package com.example.pokemonapp.ui.components.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.model.PokemonList

import com.example.pokemonapp.databinding.PokemonRowBinding

class PokemonListViewHolder(binding: PokemonRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: PokemonRowBinding? = null

    init {
        this.binding = binding
    }

    fun updateCheckbox(model: PokemonList) {
        binding?.let { view ->
            view.checkbox.isChecked = model.pokemonSpecie.selected
        }
    }

    fun setItem(model: PokemonList) {
        binding?.let { view ->
            view.pokemonName = model.pokemonSpecie.pokemonName
            view.checkbox.isChecked = model.pokemonSpecie.selected
            view.checkbox.setOnClickListener {
                view.pokeLayout.callOnClick()
            }
        }
    }



}
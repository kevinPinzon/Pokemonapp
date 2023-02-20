package com.example.pokemonapp.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.model.Pokedex
import com.example.pokemonapp.databinding.PokedexRowBinding

class PokedexItemViewHolder(binding: PokedexRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: PokedexRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: Pokedex) {
        binding?.let { view->
            view.pokedexName = model.name
        }
    }
}
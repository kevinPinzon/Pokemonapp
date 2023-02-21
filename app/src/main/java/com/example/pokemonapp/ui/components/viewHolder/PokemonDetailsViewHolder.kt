package com.example.pokemonapp.ui.components.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.databinding.PokemondetailsRowBinding

class PokemonDetailsViewHolder(binding: PokemondetailsRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: PokemondetailsRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: Pokemon) {
        binding?.let { view->
            view.pokemonName = "Name: ${model.pokemonName}"
            view.pokemonNumber = "Number: ${model.pokemonNumber}"
            view.pokemonType = "Type: ${model.pokemonType}"
            Glide.with(binding!!.root)
                .load(model.pokemonImg)
                .placeholder(R.mipmap.pokemon)
                .error(R.mipmap.pokemon)
                .into(view.ivPokemon)

            view.pokemonImage = model.pokemonImg
        }
    }
}
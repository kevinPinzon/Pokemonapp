package com.example.pokemonapp.ui.components.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonList

import com.example.pokemonapp.databinding.PokemonRowBinding

class PokemonListViewHolder(binding: PokemonRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: PokemonRowBinding? = null
    private val IMAGE_URL: String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"

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

            var pokemonId: String = model.pokemonSpecie.pokemonNumber.split("/pokemon-species/")[1]
            Glide.with(binding!!.root)
                .load("$IMAGE_URL${pokemonId.split("/")[0]}.png")
                .placeholder(R.mipmap.pokemon)
                .error(R.mipmap.pokemon)
                .into(view.ivPokemon)

        }
    }



}
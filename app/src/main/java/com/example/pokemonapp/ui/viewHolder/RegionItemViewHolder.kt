package com.example.pokemonapp.ui.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.model.PokeRegion
import com.example.pokemonapp.databinding.RegionRowBinding

class RegionItemViewHolder(binding: RegionRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: RegionRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: PokeRegion) {
        binding?.let { view->
            view.regionName = model.name
//            view.textViewCountry.text = model.name
        }
    }
}
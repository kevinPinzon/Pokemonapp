package com.example.pokemonapp.ui.components.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.databinding.TeamRowBinding

class TeamListItemViewHolder(binding: TeamRowBinding): RecyclerView.ViewHolder(binding.root) {
    private var binding: TeamRowBinding? = null

    init {
        this.binding = binding
    }

    fun setItem(model: Team) {
        binding?.let { view->
            view.teamName = model.name
        }
    }
}
package com.example.pokemonapp.ui.components.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokemonList
import com.example.pokemonapp.databinding.PokemonRowBinding
import com.example.pokemonapp.ui.pokemonlist.ClickListenerPokemon

import com.example.pokemonapp.ui.components.viewHolder.PokemonListViewHolder

class PokemonListAdapter (private val listener: ClickListenerPokemon):
    RecyclerView.Adapter<PokemonListViewHolder>() {

    private val resource = R.layout.pokemon_row
    lateinit var context: Context
    private val itemList = mutableListOf<PokemonList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonListViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: PokemonRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return PokemonListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PokemonListViewHolder, position: Int) {
        holder.setItem(itemList[position])
        holder.itemView.setOnClickListener {
            listener.pokemonSelect(itemList[position])
            holder.updateCheckbox(itemList[position])
        }
    }

    fun setItems(list: MutableList<PokemonList>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

}
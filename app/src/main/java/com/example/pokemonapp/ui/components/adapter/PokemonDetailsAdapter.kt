package com.example.pokemonapp.ui.components.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.Pokemon
import com.example.pokemonapp.databinding.PokemondetailsRowBinding
import com.example.pokemonapp.ui.components.viewHolder.PokemonDetailsViewHolder

class PokemonDetailsAdapter():
    RecyclerView.Adapter<PokemonDetailsViewHolder>() {

    private val resource = R.layout.pokemondetails_row
    lateinit var context: Context
    private val itemList = mutableListOf<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonDetailsViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: PokemondetailsRowBinding = DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return PokemonDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PokemonDetailsViewHolder, position: Int) {
        holder.setItem(itemList[position])
        holder.itemView.setOnClickListener {
        }
    }

    fun setItems(list: MutableList<Pokemon>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

}
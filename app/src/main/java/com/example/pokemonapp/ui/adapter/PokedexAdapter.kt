package com.example.pokemonapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.data.model.Pokedex
import com.example.pokemonapp.ui.pokedexlist.ClickListenerPokedex
import com.example.pokemonapp.ui.viewHolder.PokedexItemViewHolder
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.pokemonapp.R
import com.example.pokemonapp.databinding.PokedexRowBinding

class PokedexAdapter (private val listener: ClickListenerPokedex):
    RecyclerView.Adapter<PokedexItemViewHolder>() {

    private val resource = R.layout.pokedex_row
    lateinit var context: Context
    private val itemList = mutableListOf<Pokedex>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokedexItemViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: PokedexRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return PokedexItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: PokedexItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.pokedexSelect(itemList[position])
        }
    }

    fun setItems(list: MutableList<Pokedex>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

}
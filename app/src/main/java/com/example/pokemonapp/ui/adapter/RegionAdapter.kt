package com.example.pokemonapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.PokeRegion
import com.example.pokemonapp.databinding.RegionRowBinding
import com.example.pokemonapp.ui.home.ClickListener
import com.example.pokemonapp.ui.viewHolder.RegionItemViewHolder

class RegionAdapter (private val listener: ClickListener):
    RecyclerView.Adapter<RegionItemViewHolder>() {
    private val resource = R.layout.region_row
    lateinit var context: Context
    private val itemList = mutableListOf<PokeRegion>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegionItemViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: RegionRowBinding =
            DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return RegionItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: RegionItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.regionSelect(itemList[position])
        }
    }

    fun setItems(list: MutableList<PokeRegion>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }

}
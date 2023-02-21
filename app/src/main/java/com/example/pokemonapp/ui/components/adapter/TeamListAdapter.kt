package com.example.pokemonapp.ui.components.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapp.R
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.databinding.TeamRowBinding
import com.example.pokemonapp.ui.components.viewHolder.TeamListItemViewHolder
import com.example.pokemonapp.ui.team.ClickListenerTeam

class TeamListAdapter (private val listener: ClickListenerTeam):
    RecyclerView.Adapter<TeamListItemViewHolder>() {

    private val resource = R.layout.team_row
    lateinit var context: Context
    private val itemList = mutableListOf<Team>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamListItemViewHolder {
        context = parent.context
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: TeamRowBinding = DataBindingUtil.inflate(layoutInflater, resource, parent, false)
        return TeamListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: TeamListItemViewHolder, position: Int) {
        holder.setItem(itemList[position])

        holder.itemView.setOnClickListener {
            listener.teamSelect(itemList[position])
        }
    }

    fun setItems(list: MutableList<Team>) {
        itemList.clear()
        itemList.addAll(list)
        notifyDataSetChanged()
    }
}
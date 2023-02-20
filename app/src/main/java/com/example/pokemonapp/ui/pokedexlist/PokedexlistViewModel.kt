package com.example.pokemonapp.ui.pokedexlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.Pokedex
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokedexlistViewModel : ViewModel() {

    val pokedexLiveList: MutableLiveData<MutableList<Pokedex>> = MutableLiveData()

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = PokemonRepository()

    fun getPokedexes(regionName: String) {
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getPokedex(regionName)
            response?.body()?.let { list->
                pokedexLiveList.value = list.pokedexResult
            }
        }
    }


}
package com.example.pokemonapp.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.PokemonList
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonlistViewModel : ViewModel() {

    val pokedexDescription: MutableLiveData<String> = MutableLiveData()
    val pokedexName: MutableLiveData<String> = MutableLiveData()
    val pokemonLiveList: MutableLiveData<MutableList<PokemonList>> = MutableLiveData()
    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = PokemonRepository()

    fun getPokemonByPokedex(name: String) {
        _progressState.value = true
        viewModelScope.launch {
            val response = repository.getPokemonByPokedex(name)
            response?.body()?.let { list->
                pokedexName.value = list.pokedexName
                pokedexDescription.value =
                    list.PokedexDescription[list.PokedexDescription.lastIndex].description
                pokemonLiveList.value = list.pokemons
            }
        }
    }
}
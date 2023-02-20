package com.example.pokemonapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.PokeRegion
import com.example.pokemonapp.domain.repository.PokemonRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
    val regionsLiveList: MutableLiveData<MutableList<PokeRegion>> = MutableLiveData()

    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val repository = PokemonRepository()

    fun getRegions() {
        _progressState.value = true
        viewModelScope.launch {
        val response = repository.getRegions()
            response?.body()?.let { list->
                regionsLiveList.value = list.regionResult
            }

            println("resultRegions: $regionsLiveList")
        }
    }

}
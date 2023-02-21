package com.example.pokemonapp.ui.pokemonlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.*
import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.domain.usecase.FirebaseCreateTeam
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonlistViewModel @Inject constructor(
    private val createTeamUseCase: FirebaseCreateTeam
): ViewModel() {

    val pokedexDescription: MutableLiveData<String> = MutableLiveData()
    val pokedexName: MutableLiveData<String> = MutableLiveData()
    val pokemonLiveList: MutableLiveData<MutableList<PokemonList>> = MutableLiveData()
    private val _progressState = MutableLiveData<Boolean>()
    var progressState: LiveData<Boolean> = _progressState

    private val _creatTeamState = MutableLiveData<Resource<Boolean>>()
    var creatTeamState: LiveData<Resource<Boolean>> = _creatTeamState
    private var pokemonListData: MutableList<Pokemon> = mutableListOf()

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

    suspend fun getPokemonData(pokemonSelected: ArrayList<PokemonSpecie>){
//        _progressState.value = true
        pokemonListData.clear()
        pokemonSelected.forEach{ pokeElement ->
            val response = repository.getPokemonData(pokeElement.pokemonName)
            response?.body()?.let { pokeData->
                var pokeType: String = ""
                pokeData?.pokeType?.forEachIndexed { index, element ->
                    pokeType = if (index == 0) (element?.pokeTypeName?.name ?: "")
                    else "$pokeType - ${element.pokeTypeName?.name}"
                }
                pokemonListData.add(Pokemon(
                    pokemonName = pokeElement.pokemonName,
                    pokemonNumber = pokeData.pokeNumber,
                    pokemonType = pokeType,
                    pokemonImg = pokeData.pokeSprites.pokeImage,
                    pokedexDescription = pokedexDescription?.value ?: ""
                ))
            }
        }
    }

    fun createTeam(team: Team, pokemonSelected: ArrayList<PokemonSpecie>) {
        viewModelScope.launch {
            getPokemonData(pokemonSelected)
            team.pokemons = pokemonListData
            createTeamUseCase(team).onEach {
                _creatTeamState.value = it
            }.launchIn(viewModelScope)
        }
    }
}
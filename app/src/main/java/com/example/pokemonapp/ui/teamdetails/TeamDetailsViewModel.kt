package com.example.pokemonapp.ui.teamdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.PokemonSpecie
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.domain.repository.PokemonRepository
import com.example.pokemonapp.domain.usecase.FirebaseDeleteTeam
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailsViewModel @Inject constructor(
    private val deleteTeamUseCase: FirebaseDeleteTeam
): ViewModel() {

    private val _deleteTeamState = MutableLiveData<Resource<Boolean>>()
    var deleteTeamState: LiveData<Resource<Boolean>> = _deleteTeamState

    fun deleteTeam(team: Team, userID: String) {
        viewModelScope.launch {
            team.enable = false
            deleteTeamUseCase(team, userID).onEach {
                _deleteTeamState.value = it
            }.launchIn(viewModelScope)
        }
    }

}
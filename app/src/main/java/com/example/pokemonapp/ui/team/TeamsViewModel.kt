package com.example.pokemonapp.ui.team

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.data.model.User
import com.example.pokemonapp.domain.usecase.FirebaseGetTeams
import com.example.pokemonapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsViewModel @Inject constructor(
    private val getTeamsUseCase: FirebaseGetTeams
): ViewModel() {

    private val _teamsState: MutableLiveData<Resource<MutableList<Team>>> = MutableLiveData()
    val teamsState: LiveData<Resource<MutableList<Team>>>
    get() = _teamsState

    fun getTeams(userID: String) {
        viewModelScope.launch {
            getTeamsUseCase(userID).onEach { state ->
                _teamsState.value = state
            }.launchIn(viewModelScope)
        }
    }
}
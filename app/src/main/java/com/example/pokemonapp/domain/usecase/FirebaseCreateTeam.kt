package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.domain.repository.TeamRepository
import com.example.pokemonapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseCreateTeam @Inject constructor(
    private val teamRepository: TeamRepository
) {

    suspend operator fun invoke(team: Team): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        if (teamRepository.createTeam(team)) {
            emit(Resource.Success(true))
        } else {
            emit(Resource.Error("create team error"))
        }

    }
}
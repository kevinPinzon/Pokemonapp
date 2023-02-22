package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.domain.repository.TeamRepository
import com.example.pokemonapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseDeleteTeam @Inject constructor(
    private val teamRepository: TeamRepository
) {

    suspend operator fun invoke(team: Team, userId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading)
        if (teamRepository.deleteTeams(team, userId)) {
            emit(Resource.Success(true))
        } else {
            emit(Resource.Error("delete team error"))
        }

    }
}
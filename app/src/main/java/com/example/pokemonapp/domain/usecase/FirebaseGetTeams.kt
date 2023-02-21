package com.example.pokemonapp.domain.usecase

import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.domain.repository.TeamRepository
import com.example.pokemonapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FirebaseGetTeams @Inject constructor(
    private val teamRepository: TeamRepository
) {
    suspend operator fun invoke(userId: String): Flow<Resource<MutableList<Team>>> = flow {
        emit(Resource.Loading)
        var pokemonTeams = teamRepository.getTeams(userId)

        if (!pokemonTeams.isNullOrEmpty()) {
            emit(Resource.Success(pokemonTeams))
        } else {
            emit(Resource.Error("get team error"))
        }

    }
}
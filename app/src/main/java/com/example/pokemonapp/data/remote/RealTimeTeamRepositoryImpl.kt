package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.domain.repository.TeamRepository
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RealTimeTeamRepositoryImpl @Inject constructor(
    private val realtime: FirebaseDatabase
):TeamRepository {

    override suspend fun createTeam(team: Team): Boolean {
        return try {
            var isSuccessful = true
            realtime.getReference("teams")
                .child(team.id).setValue(team)
                .addOnCompleteListener {
                    isSuccessful = it.isSuccessful
                }
                .await()
            isSuccessful
        } catch (e: Exception) {
            false
        }
    }

}
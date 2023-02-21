package com.example.pokemonapp.data.remote

import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.data.model.User
import com.example.pokemonapp.domain.repository.TeamRepository
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RealTimeTeamRepositoryImpl @Inject constructor(
    private val realtime: FirebaseDatabase
):TeamRepository {

    override suspend fun createTeam(team: Team, userId: String): Boolean {
        return try {
            var isSuccessful = true
            realtime.getReference("users")
                .child(userId).child("teams").child(team.id).setValue(team)
                .addOnCompleteListener {
                    isSuccessful = it.isSuccessful
                }
                .await()
            isSuccessful
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun getTeams(userId: String): MutableList<Team> {
        return try {
                var pokemonTeams : MutableList<Team> = mutableListOf()
            realtime.getReference("users")
                .child(userId).child("teams").get()
                .addOnSuccessListener { response ->
                    var result = response.value as HashMap<String, Team>
                    result.map { element ->
                        pokemonTeams.add(element.value)
                    }
                }.await()
            pokemonTeams
        } catch (e: Exception) {
            mutableListOf()
        }
    }

}
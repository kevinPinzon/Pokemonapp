package com.example.pokemonapp.data.remote

import android.util.Log
import com.example.pokemonapp.data.model.Team
import com.example.pokemonapp.domain.repository.TeamRepository
import com.google.firebase.database.FirebaseDatabase
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
                    response.children.forEach { teamSnapShot ->
                        var team: Team? = teamSnapShot.getValue(Team::class.java)
                        team?.let {
                            if (it.enable)
                                pokemonTeams.add(it)
                        }
                    }
                }
                .addOnFailureListener{
                    Log.e("firebase", "Error getting data", it)
                }.await()
            pokemonTeams
        } catch (e: Exception) {
            mutableListOf()
        }
    }

    override suspend fun deleteTeams(team: Team, userId: String): Boolean {
        return try {
            var isSuccessful = true

            val childUpdates = hashMapOf<String, Any>(
                "/teams/${team.id}" to team
            )
            realtime.getReference("users")
                .child(userId).updateChildren(childUpdates)
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
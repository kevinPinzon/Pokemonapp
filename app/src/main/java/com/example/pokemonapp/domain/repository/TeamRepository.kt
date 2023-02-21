package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.data.model.Team

interface TeamRepository {

    suspend fun createTeam(team: Team, userId: String): Boolean

    suspend fun getTeams(userId: String): MutableList<Team>
}
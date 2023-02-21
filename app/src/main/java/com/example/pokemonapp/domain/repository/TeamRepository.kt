package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.data.model.Team

interface TeamRepository {

    suspend fun createTeam(team: Team): Boolean
}
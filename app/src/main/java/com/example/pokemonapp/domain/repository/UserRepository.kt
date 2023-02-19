package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.domain.model.User

interface UserRepository {

    suspend fun createUser(user: User): Boolean

    suspend fun getUser(userId: String): User
}
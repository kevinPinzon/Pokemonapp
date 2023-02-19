package com.example.pokemonapp.domain.repository

interface AuthRepository {

    suspend fun login(email: String, password: String): String
    
    suspend fun signup(email: String, password: String): String
}
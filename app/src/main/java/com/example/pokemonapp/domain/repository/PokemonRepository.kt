package com.example.pokemonapp.domain.repository

import com.example.pokemonapp.services.APIservice
import com.example.pokemonapp.services.RetrofitClient

class PokemonRepository {
    private var apiService: APIservice? = null

    init {
        apiService = RetrofitClient.getClient?.create(APIservice::class.java)

    }

    suspend fun getRegions() = apiService?.getRegions("region")

    suspend fun getPokedex(regionName: String) = apiService?.getPokedexes("region/$regionName")

    suspend fun getPokemonByPokedex(pokedexName: String) = apiService?.getPokemonByPokedex("pokedex/$pokedexName")

}
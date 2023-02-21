package com.example.pokemonapp.services

import com.example.pokemonapp.data.model.PokedexResult
import com.example.pokemonapp.data.model.PokemonDataResult
import com.example.pokemonapp.data.model.PokemonListResult
import com.example.pokemonapp.data.model.RegionResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIservice {

    @GET
    suspend fun getRegions(@Url url:String): Response<RegionResult>

    @GET
    suspend fun getPokedexes(@Url url:String): Response<PokedexResult>

    @GET
    suspend fun getPokemonByPokedex(@Url url:String): Response<PokemonListResult>

    @GET
    suspend fun getPokemonData(@Url url:String): Response<PokemonDataResult>

}
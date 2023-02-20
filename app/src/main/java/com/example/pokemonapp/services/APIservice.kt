package com.example.pokemonapp.services

import com.example.pokemonapp.data.model.RegionResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIservice {

    @GET
    suspend fun getRegions(@Url url:String): Response<RegionResult>

}
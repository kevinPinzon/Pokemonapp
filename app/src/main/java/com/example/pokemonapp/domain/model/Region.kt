package com.example.pokemonapp.domain.model

import com.google.gson.annotations.SerializedName

data class RegionResult(
    @SerializedName("results") var regionResult: List<PokeRegion>
)

data class PokeRegion(
    @SerializedName("name") var name: String,
    @SerializedName("url") var url: String,
)

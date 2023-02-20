package com.example.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class RegionResult(
    @SerializedName("results") val regionResult: MutableList<PokeRegion> = mutableListOf()
)

data class PokeRegion(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)

package com.example.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class PokedexResult(
    @SerializedName("pokedexes") val pokedexResult: MutableList<Pokedex> = mutableListOf()
)

data class Pokedex(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
)

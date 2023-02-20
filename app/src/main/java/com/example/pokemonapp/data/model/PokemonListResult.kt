package com.example.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResult(
    @SerializedName("name") val pokedexName: String,
    @SerializedName("descriptions") val PokedexDescription: MutableList<PokedexDescription> = mutableListOf(),
    @SerializedName("pokemon_entries") val pokemons: MutableList<PokemonList> = mutableListOf()
)

data class PokedexDescription(
    @SerializedName("description") val description: String
)

data class PokemonList(
    @SerializedName("pokemon_species") val pokemonSpecie: PokemonSpecie
)

data class PokemonSpecie(
    @SerializedName("name") val pokemonName: String
)

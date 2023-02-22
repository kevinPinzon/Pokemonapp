package com.example.pokemonapp.data.model

import java.io.Serializable

data class Team(
    var id: String = "",
    var name: String = "",
    var pokemons: MutableList<Pokemon> = mutableListOf(),
    var enable: Boolean = true
) : Serializable

data class Pokemon(
    val pokemonName: String = "",
    var pokemonNumber: Int = 0,
    var pokemonType: String = "",
    var pokedexDescription: String = "",
    var pokemonImg: String = ""

)
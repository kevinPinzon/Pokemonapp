package com.example.pokemonapp.data.model


data class Team(
    var id: String = "",
    var name: String = "",
    var pokemons: MutableList<Pokemon> = mutableListOf(),
    var idTrainer: String,
)

data class Pokemon(
    val pokemonName: String = "",
    var pokemonNumber: Int = 0,
    var pokemonType: String = "",
    var pokedexDescription: String = "",
    var pokemonImg: String = ""

)
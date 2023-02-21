package com.example.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonDataResult(
    @SerializedName("order") val pokeNumber: Int = 0,
    @SerializedName("types") val pokeType: MutableList<PokeType>? = mutableListOf(),
    @SerializedName("sprites") val pokeSprites: PokeSprite = PokeSprite(),
)

data class PokeType(
    @SerializedName("type") val pokeTypeName: PokeTypeName?
)

data class PokeTypeName(
    @SerializedName("name") val name: String?
)

data class PokeSprite(
    @SerializedName("front_default") val pokeImage: String = ""
)

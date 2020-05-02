package com.tem.plate._util

import com.tem.domain.boundary.PokemonRepository
import com.tem.domain.entity.Pokemon

class MockDefaultPokemonRepository : PokemonRepository {

    private val firstPageMocked = listOf(
        Pokemon(name = "1"),
        Pokemon(name = "2"),
        Pokemon(name = "3"),
        Pokemon(name = "4"),
        Pokemon(name = "5"),
        Pokemon(name = "6"),
        Pokemon(name = "7"),
        Pokemon(name = "8"),
        Pokemon(name = "9"),
        Pokemon( name = "10")
    )

    private val secondPageMocked = listOf(
        Pokemon(name = "11"),
        Pokemon(name = "12"),
        Pokemon(name = "13"),
        Pokemon(name = "14"),
        Pokemon(name = "15"),
        Pokemon(name = "16"),
        Pokemon(name = "17"),
        Pokemon(name = "18"),
        Pokemon(name = "19"),
        Pokemon(name = "20")
    )

    override suspend fun getPokemon(): Pokemon {
        return firstPageMocked.first()
    }
}
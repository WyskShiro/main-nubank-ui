package com.tem.plate._util

import com.tem.domain.boundary.PokemonRepository
import org.koin.dsl.module

val mockedRepositoryModule = module {
    factory<PokemonRepository> { MockDefaultPokemonRepository() }
}
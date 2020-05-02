package com.tem.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Declare urls for API
 */
interface ApiService {

    @GET("a")
    suspend fun getPokemon(@Path("id") pokemonId: Int): Response<Any>
}

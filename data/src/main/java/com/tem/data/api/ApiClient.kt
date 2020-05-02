package com.tem.data.api

import javax.inject.Inject

/**
 * The bridge between ApiService and DefaultRepositories
 * */
class ApiClient @Inject constructor(
    private val apiService: ApiService
) : RequestHandler() {

    suspend fun getA(): Any? {
        return makeRequest {
            apiService.getPokemon(1)
        }
    }
}

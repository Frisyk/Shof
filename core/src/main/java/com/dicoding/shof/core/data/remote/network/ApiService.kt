package com.dicoding.shof.core.data.remote.network

import com.dicoding.shof.core.data.remote.response.GamesDestailsResponse
import com.dicoding.shof.core.data.remote.response.GamesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

const val mKey = com.dicoding.shof.core.BuildConfig.KEY

interface ApiService {
    @GET("games")
    suspend fun getList(
        @Query("key") key: String = mKey,
        @Query("search") search: String? = null
        ): GamesResponse

    @GET("games/{id}")
    suspend fun getDetails(
        @Path("id") id: Int,
        @Query("key") key: String = mKey
        ) : GamesDestailsResponse
}

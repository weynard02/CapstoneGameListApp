package com.weynard02.core.data.source.remote.network

import com.weynard02.core.BuildConfig
import com.weynard02.core.data.source.remote.response.ListGameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getGames(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("search") search: String? = null,
    ) : ListGameResponse

}
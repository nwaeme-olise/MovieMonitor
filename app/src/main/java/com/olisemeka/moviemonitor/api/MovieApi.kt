package com.olisemeka.moviemonitor.api

import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.util.Constants.API_KEY
import com.olisemeka.moviemonitor.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("list/{list_id}")
    suspend fun getMovieListResults(
        @Path("list_id")
        listId: Int,

        @Query("sort_by")
        sortBy: String,

        @Query("api_key")
    apiKey: String = API_KEY,
    ): MovieListResult
}
package com.olisemeka.moviemonitor

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("list/{list_id}")
    fun getMovieListResults(
        @Path("list_id")
        listId: Int,

        @Query("api_key")
        apiKey: String,

        @Query("sort_by")
        sortBy: String
    ): Call<List<MovieListResult>>
}
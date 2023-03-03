package com.olisemeka.moviemonitor.api

import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.util.Constants.API_KEY
import com.olisemeka.moviemonitor.util.Constants.RELEASE_DATE_LTE
import com.olisemeka.moviemonitor.util.Constants.SORT_ORDER
import com.olisemeka.moviemonitor.util.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("discover/movie")
    suspend fun getMovieListResults(
        @Query("sort_by")
        sortBy: String = SORT_ORDER,

        @Query("page")
        page: Int,

        @Query("release_date.lte")
        releaseDateLTE: String = RELEASE_DATE_LTE,

        @Query("api_key")
    apiKey: String = API_KEY,
    ): MovieListResult
}
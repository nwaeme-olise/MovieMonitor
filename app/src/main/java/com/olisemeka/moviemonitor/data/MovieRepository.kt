package com.olisemeka.moviemonitor.data

import com.olisemeka.moviemonitor.api.RetrofitInstance

class MovieRepository {
    suspend fun getMovieListResults(listId: Int, sortBy: String) =
        RetrofitInstance.api.getMovieListResults(listId, sortBy)
}
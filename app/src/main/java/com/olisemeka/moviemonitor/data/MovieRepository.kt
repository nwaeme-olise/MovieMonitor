package com.olisemeka.moviemonitor.data

import com.olisemeka.moviemonitor.api.RetrofitInstance
import com.olisemeka.moviemonitor.util.Resource

class MovieRepository {
    suspend fun getMovieListResults(listId: Int, sortBy: String): Resource<MovieListResult> {
        return try {
            val response = RetrofitInstance.api.getMovieListResults(listId, sortBy)
            Resource.Success(response)
        }
        catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown Error")
        }
    }
}

package com.olisemeka.moviemonitor.data.repository

import com.olisemeka.moviemonitor.api.MovieApi
import com.olisemeka.moviemonitor.api.RetrofitInstance
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (private val movieApi: MovieApi): MovieRepository {
    override suspend fun getMovieListResults(listId: Int, sortBy: String): Resource<MovieListResult> {
        return try {
            val response = movieApi.getMovieListResults(listId, sortBy)
            Resource.Success(response)
        }
        catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown Error")
        }
    }
}

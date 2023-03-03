package com.olisemeka.moviemonitor.data.repository

import com.olisemeka.moviemonitor.api.MovieApi
import com.olisemeka.moviemonitor.api.RetrofitInstance
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (private val movieApi: MovieApi): MovieRepository {
    override suspend fun getMovieListResults(): Resource<MovieListResult> {
        var page = 1
        val resultList = mutableListOf<MovieResult>()
        return try {
            while (true) {
                val response = movieApi.getMovieListResults(page = page)
                resultList.addAll(response.results)
                if (page == 20){
                    break
                }
                page++
            }
            val result = MovieListResult(resultList, resultList.size)
            Resource.Success(result)
        }
        catch (e: Exception) {
            Resource.Error(message = e.message ?: "Unknown Error")
        }
    }
}

package com.olisemeka.moviemonitor.data.repository

import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.util.Resource

interface MovieRepository {
    suspend fun getMovieListResults(listId: Int, sortBy: String): Resource<MovieListResult>
}
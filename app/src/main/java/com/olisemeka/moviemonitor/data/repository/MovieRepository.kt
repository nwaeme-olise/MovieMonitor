package com.olisemeka.moviemonitor.data.repository

import androidx.paging.PagingData
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieResults(): Flow<PagingData<MovieResult>>
}
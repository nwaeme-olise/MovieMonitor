package com.olisemeka.moviemonitor.data.repository

import androidx.paging.PagingData
import com.olisemeka.moviemonitor.data.model.MovieResult
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieResults(): Flow<PagingData<MovieResult>>
}
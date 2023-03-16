package com.olisemeka.moviemonitor.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.olisemeka.moviemonitor.api.MovieApi
import com.olisemeka.moviemonitor.api.RetrofitInstance
import com.olisemeka.moviemonitor.data.MovieListResult
import com.olisemeka.moviemonitor.data.MoviePagingSource
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.util.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (private val movieApi: MovieApi): MovieRepository {
    override fun getMovieResults(): Flow<PagingData<MovieResult>> =
        Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { MoviePagingSource(movieApi) }
        ).flow
}

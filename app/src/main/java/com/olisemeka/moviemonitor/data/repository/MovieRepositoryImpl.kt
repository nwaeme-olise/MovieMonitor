package com.olisemeka.moviemonitor.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.olisemeka.moviemonitor.api.MovieApi
import com.olisemeka.moviemonitor.data.model.MovieResult
import com.olisemeka.moviemonitor.data.database.MovieDatabase
import com.olisemeka.moviemonitor.data.paging.MovieRemoteMediator
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
@OptIn(ExperimentalPagingApi::class)
class MovieRepositoryImpl @Inject constructor (
    private val movieApi: MovieApi,
    private val movieDatabase: MovieDatabase
    ): MovieRepository {

    override fun getMovieResults(): Flow<PagingData<MovieResult>> =
        Pager(config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { movieDatabase.getMovieDao().getAllMovies() },
        remoteMediator = MovieRemoteMediator(movieApi, movieDatabase)
        ).flow
}

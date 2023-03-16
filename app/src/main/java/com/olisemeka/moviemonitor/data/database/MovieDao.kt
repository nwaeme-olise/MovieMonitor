package com.olisemeka.moviemonitor.data.database

import androidx.paging.PagingSource
import androidx.room.*
import com.olisemeka.moviemonitor.data.MovieResult

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movieList: List<MovieResult>)

    @Query("SELECT * FROM movie")
    fun getAllMovies(): PagingSource<Int, MovieResult>

    @Query("DELETE FROM movie")
    suspend fun clearAll()
}
package com.olisemeka.moviemonitor.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olisemeka.moviemonitor.data.MovieResult

@Database(entities = [MovieResult::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
}
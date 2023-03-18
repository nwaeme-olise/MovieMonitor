package com.olisemeka.moviemonitor.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.data.model.MovieRemoteKeys

@Database(entities = [MovieResult::class, MovieRemoteKeys::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun getMovieDao(): MovieDao
    abstract fun getMovieRemoteKeysDao(): MovieRemoteKeysDao

    inline fun <T> withTransaction(block: () -> T): T {
        beginTransaction()
        return try {
            val result = block()
            setTransactionSuccessful()
            result
        } finally {
            endTransaction()
        }
    }
}
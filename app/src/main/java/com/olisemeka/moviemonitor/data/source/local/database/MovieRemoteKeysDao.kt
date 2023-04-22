package com.olisemeka.moviemonitor.data.source.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.olisemeka.moviemonitor.data.source.local.model.MovieRemoteKeys

@Dao
interface MovieRemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRemoteKeys(remoteKeys: List<MovieRemoteKeys>)

    @Query("SELECT * FROM remote_keys where id=:id")
    fun getAllRemoteKeys(id: Int): MovieRemoteKeys

    @Query("DELETE FROM remote_keys")
    fun deleteAllRemoteKeys()
}
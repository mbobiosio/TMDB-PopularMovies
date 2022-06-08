package com.mbobiosio.popularmovies.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbobiosio.popularmovies.data.local.entity.RemoteKeyEntity

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Dao
interface RemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeyEntity>)

    @Query("SELECT * FROM popular_movie_remote_keys WHERE movieId = :id")
    suspend fun remoteKeyId(id: Long): RemoteKeyEntity?

    @Query("DELETE FROM popular_movie_remote_keys")
    suspend fun deleteAllRemoteKeys()
}

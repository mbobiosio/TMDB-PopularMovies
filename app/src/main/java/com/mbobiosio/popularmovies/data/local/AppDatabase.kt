package com.mbobiosio.popularmovies.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mbobiosio.popularmovies.data.local.dao.PopularMovieDao
import com.mbobiosio.popularmovies.data.local.dao.RemoteKeyDao
import com.mbobiosio.popularmovies.data.local.entity.PopularMovie
import com.mbobiosio.popularmovies.data.local.entity.RemoteKeyEntity

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Database(
    entities = [PopularMovie::class, RemoteKeyEntity::class],
    exportSchema = false,
    version = 2
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val popularMovieDao: PopularMovieDao
    abstract val popularMovieRemoteDao: RemoteKeyDao
}

package com.mbobiosio.popularmovies.di

import android.content.Context
import androidx.room.Room
import com.mbobiosio.popularmovies.data.local.AppDatabase
import com.mbobiosio.popularmovies.data.local.dao.PopularMovieDao
import com.mbobiosio.popularmovies.data.local.dao.RemoteKeyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "tmdb_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideMoviesDao(appDatabase: AppDatabase): PopularMovieDao = appDatabase.popularMovieDao

    @Provides
    fun provideRemoteKeyDao(appDatabase: AppDatabase): RemoteKeyDao = appDatabase.popularMovieRemoteDao
}

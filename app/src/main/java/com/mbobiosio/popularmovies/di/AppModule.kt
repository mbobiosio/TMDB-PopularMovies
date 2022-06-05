package com.mbobiosio.popularmovies.di

import androidx.paging.ExperimentalPagingApi
import com.mbobiosio.popularmovies.data.local.AppDatabase
import com.mbobiosio.popularmovies.data.remote.api.ApiService
import com.mbobiosio.popularmovies.data.remote.repository.MovieRepositoryImpl
import com.mbobiosio.popularmovies.domain.repository.MovieRepository
import com.mbobiosio.popularmovies.domain.usecase.PopularMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Module
@InstallIn(SingletonComponent::class)
@ExperimentalPagingApi
class AppModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        service: ApiService,
        database: AppDatabase
    ): MovieRepository = MovieRepositoryImpl(service, database)

    @Provides
    @Singleton
    fun provideMoviesUseCase(
        movieRepository: MovieRepository
    ): PopularMoviesUseCase = PopularMoviesUseCase(movieRepository)
}

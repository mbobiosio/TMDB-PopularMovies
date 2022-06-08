package com.mbobiosio.popularmovies.di

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
class AppModule {

    @Provides
    fun provideMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository = movieRepositoryImpl

    @Provides
    @Singleton
    fun provideMoviesUseCase(
        movieRepository: MovieRepository
    ): PopularMoviesUseCase = PopularMoviesUseCase(movieRepository)
}

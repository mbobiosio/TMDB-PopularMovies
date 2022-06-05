package com.mbobiosio.popularmovies.domain.usecase

import androidx.paging.PagingData
import com.mbobiosio.popularmovies.data.remote.model.movie.Movie
import com.mbobiosio.popularmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class PopularMoviesUseCase(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(): Flow<PagingData<Movie>> =
        movieRepository.getPopularMovies()
}

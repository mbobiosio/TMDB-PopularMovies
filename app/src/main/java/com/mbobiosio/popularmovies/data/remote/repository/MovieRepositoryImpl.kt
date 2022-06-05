package com.mbobiosio.popularmovies.data.remote.repository

import androidx.paging.* // ktlint-disable no-wildcard-imports
import com.mbobiosio.popularmovies.data.local.AppDatabase
import com.mbobiosio.popularmovies.data.mappers.entityToModel
import com.mbobiosio.popularmovies.data.remote.api.ApiService
import com.mbobiosio.popularmovies.data.remote.model.movie.Movie
import com.mbobiosio.popularmovies.data.remote.paging.PopularMoviesMediator
import com.mbobiosio.popularmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@ExperimentalPagingApi
class MovieRepositoryImpl(
    private val service: ApiService,
    private val database: AppDatabase
) : MovieRepository {

    override fun getPopularMovies(): Flow<PagingData<Movie>> {
        Timber.d("Called")
        val pagingSourceFactory = { database.moviesDao.getMovies() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = PagingConfig.MAX_SIZE_UNBOUNDED,
                jumpThreshold = Int.MIN_VALUE,
                enablePlaceholders = true,
            ),
            remoteMediator = PopularMoviesMediator(
                service,
                database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { data ->
            data.map { entity ->
                entityToModel(entity)
            }
        }
    }
}

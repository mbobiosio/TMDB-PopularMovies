package com.mbobiosio.popularmovies.data.remote.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mbobiosio.popularmovies.data.local.AppDatabase
import com.mbobiosio.popularmovies.data.local.entity.PopularMovie
import com.mbobiosio.popularmovies.data.remote.paging.PopularMoviesMediator
import com.mbobiosio.popularmovies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val database: AppDatabase,
    private val popularMoviesMediator: PopularMoviesMediator
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovies(): Flow<PagingData<PopularMovie>> {

        val pagingSourceFactory = { database.popularMovieDao.getMovies() }

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            remoteMediator = popularMoviesMediator,
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}

package com.mbobiosio.popularmovies.data.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mbobiosio.popularmovies.data.local.AppDatabase
import com.mbobiosio.popularmovies.data.local.entity.PopularMovie
import com.mbobiosio.popularmovies.data.local.entity.RemoteKeyEntity
import com.mbobiosio.popularmovies.data.mappers.mapDataToPopularMovies
import com.mbobiosio.popularmovies.data.remote.api.ApiService
import com.mbobiosio.popularmovies.util.Constants.API_KEY
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Suppress("UNCHECKED_CAST")
@OptIn(ExperimentalPagingApi::class)
class PopularMoviesMediator @Inject constructor(
    private val service: ApiService,
    private val database: AppDatabase
) : RemoteMediator<Int, PopularMovie>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PopularMovie>
    ): MediatorResult {

        val page = when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }

            LoadType.APPEND -> {
                val remoteKeys = getRemoteKeyForLastItem(state)
                    ?: return MediatorResult.Error(InvalidObjectException("Result is empty"))

                val nextKey = remoteKeys.nextKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getRemoteKeyForFirstItem(state)
                    ?: return MediatorResult.Error(InvalidObjectException("Result is empty"))

                val prevKey = remoteKeys.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                prevKey
            }
        }

        try {
            val movieResponse = service.getPopularMovies(apiKey = API_KEY, page)
            val data = movieResponse.mapDataToPopularMovies()

            database.withTransaction {
                // clear all tables in the database
                if (loadType == LoadType.REFRESH) {
                    database.popularMovieRemoteDao.deleteAllRemoteKeys()
                    database.popularMovieDao.deleteAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (data.isEndOfListReached) null else page + 1
                val keys = data.movies.map {
                    RemoteKeyEntity(
                        movieId = it.movieId,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                database.popularMovieRemoteDao.insertAll(keys)
                database.popularMovieDao.insertMovies(data.movies)
            }

            return MediatorResult.Success(endOfPaginationReached = data.isEndOfListReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, PopularMovie>): RemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { repo ->
            database.popularMovieRemoteDao.remoteKeyId(repo.movieId)
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, PopularMovie>): RemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { movie ->
            database.popularMovieRemoteDao.remoteKeyId(movie.movieId)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, PopularMovie>): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.movieId?.let { id ->
                database.popularMovieRemoteDao.remoteKeyId(id)
            }
        }
    }
}

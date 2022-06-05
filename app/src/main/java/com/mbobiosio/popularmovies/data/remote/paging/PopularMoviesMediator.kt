package com.mbobiosio.popularmovies.data.remote.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.mbobiosio.popularmovies.data.local.AppDatabase
import com.mbobiosio.popularmovies.data.local.entity.MovieEntity
import com.mbobiosio.popularmovies.data.local.entity.RemoteKeyEntity
import com.mbobiosio.popularmovies.data.mappers.movieToEntity
import com.mbobiosio.popularmovies.data.remote.api.ApiService
import com.mbobiosio.popularmovies.util.Constants.API_KEY
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@ExperimentalPagingApi
class PopularMoviesMediator(
    private val service: ApiService,
    private val db: AppDatabase
) : RemoteMediator<Int, MovieEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): MediatorResult {
        val page = when (val pageKeyData = getKeyPageData(loadType, state)) {
            is MediatorResult.Success -> return pageKeyData
            else -> pageKeyData as Int
        }

        try {
            Timber.d("Page $page")
            val response = service.getPopularMovies(
                apiKey = API_KEY,
                page = page
            )

            val result = response.results

            val isEndOfList = result.isEmpty()

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.moviesDao.deleteAll()
                    db.remoteKeyDao.deleteAll()
                }
                val prevKey = if (page == 1) null else page - 1
                val nextKey = if (isEndOfList) null else page + 1
                val keys = result.map {
                    RemoteKeyEntity(
                        it.id,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                db.remoteKeyDao.insertAll(keys)
                db.moviesDao.insertMovies(
                    result.map {
                        movieToEntity(it)
                    }
                )
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            val code = exception.code()
            // val message = errorResponse(exception)
            // Timber.d("$message")
            return MediatorResult.Error(exception)
        }
    }

    private suspend fun getKeyPageData(
        loadType: LoadType,
        state: PagingState<Int, MovieEntity>
    ): Any {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                Timber.d("Refresh $remoteKeys")
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                val nextKey = remoteKeys?.nextKey
                Timber.d("Append $nextKey")
                return nextKey ?: MediatorResult.Success(endOfPaginationReached = false)
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                Timber.d("Prepend ${remoteKeys?.prevKey}")
                remoteKeys?.prevKey ?: return MediatorResult.Success(
                    endOfPaginationReached = false
                )
            }
            else -> {
                val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MovieEntity>
    ): RemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.remoteId?.let { repoId ->
                db.remoteKeyDao.remoteKeyId(repoId)
            }
        }
    }

    private suspend fun getLastRemoteKey(state: PagingState<Int, MovieEntity>): RemoteKeyEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { movie -> db.remoteKeyDao.remoteKeyId(movie.remoteId) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, MovieEntity>): RemoteKeyEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { movie -> db.remoteKeyDao.remoteKeyId(movie.remoteId) }
    }
}

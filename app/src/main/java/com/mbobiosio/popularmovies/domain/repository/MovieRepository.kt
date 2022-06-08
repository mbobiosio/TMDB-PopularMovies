package com.mbobiosio.popularmovies.domain.repository

import androidx.paging.PagingData
import com.mbobiosio.popularmovies.data.local.entity.PopularMovie
import kotlinx.coroutines.flow.Flow

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<PopularMovie>>
}

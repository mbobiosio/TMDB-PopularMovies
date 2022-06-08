package com.mbobiosio.popularmovies.presentation.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mbobiosio.popularmovies.data.local.entity.PopularMovie
import com.mbobiosio.popularmovies.domain.usecase.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: PopularMoviesUseCase
) : ViewModel() {

    fun getPopularMovies(): LiveData<PagingData<PopularMovie>> {
        return moviesUseCase.invoke().asLiveData()
            .cachedIn(viewModelScope)
    }

/*
    suspend fun getPopularMovies(): Flow<PagingData<PopularMovie>> {
        return movieRepository
            .getPopularMovies()
            .cachedIn(viewModelScope)
            .flowOn(Dispatchers.IO)
    }
*/
}

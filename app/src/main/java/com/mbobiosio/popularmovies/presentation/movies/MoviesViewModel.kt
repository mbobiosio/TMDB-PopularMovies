package com.mbobiosio.popularmovies.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mbobiosio.popularmovies.domain.usecase.PopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesUseCase: PopularMoviesUseCase
) : ViewModel() {

    val movieList = moviesUseCase().asLiveData().cachedIn(viewModelScope)
}

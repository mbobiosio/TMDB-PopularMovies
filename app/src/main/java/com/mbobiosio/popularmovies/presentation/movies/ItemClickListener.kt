package com.mbobiosio.popularmovies.presentation.movies

import com.mbobiosio.popularmovies.data.remote.model.movie.Movie

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface ItemClickListener {
    fun onItemClick(movie: Movie?)
}

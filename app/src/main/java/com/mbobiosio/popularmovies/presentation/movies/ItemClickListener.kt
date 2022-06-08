package com.mbobiosio.popularmovies.presentation.movies

import com.mbobiosio.popularmovies.data.local.entity.PopularMovie

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface ItemClickListener {
    fun onItemClick(movie: PopularMovie?)
}

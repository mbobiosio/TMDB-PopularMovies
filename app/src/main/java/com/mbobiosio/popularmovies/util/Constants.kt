package com.mbobiosio.popularmovies.util

import com.mbobiosio.popularmovies.BuildConfig

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
object Constants {

    const val BASE_URL = BuildConfig.BASE_URL
    const val API_KEY = BuildConfig.API_KEY

    private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    const val BACKDROP_SIZE_780 = IMAGE_BASE_URL + "w780"
    const val PROFILE_SIZE_185 = IMAGE_BASE_URL + "w185"
}

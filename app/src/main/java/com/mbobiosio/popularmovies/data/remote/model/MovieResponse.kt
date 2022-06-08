package com.mbobiosio.popularmovies.data.remote.model

import com.mbobiosio.popularmovies.data.remote.model.movie.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@JsonClass(generateAdapter = true)
data class MovieResponse(
    val page: Int = 0,

    val results: List<Movie>,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Int = 0
)

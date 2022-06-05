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
    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: List<Movie>,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Int
)

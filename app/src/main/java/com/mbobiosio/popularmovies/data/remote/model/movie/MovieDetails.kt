package com.mbobiosio.popularmovies.data.remote.model.movie

import com.mbobiosio.popularmovies.data.remote.model.Genre
import com.mbobiosio.popularmovies.data.remote.model.graphics.Graphic
import com.mbobiosio.popularmovies.data.remote.model.video.VideoResponse
import com.squareup.moshi.Json

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
data class MovieDetails(

    @Json(name = "id")
    val id: Int,

    @Json(name = "adult")
    val adult: Boolean,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "belongs_to_collection")
    val belongsToCollection: Collection?,

    @Json(name = "budget")
    val budget: Int,

    @Json(name = "genres")
    val genres: List<Genre>?,

    @Json(name = "homepage")
    val homepage: String?,

    @Json(name = "imdb_id")
    val imdbId: String?,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "popularity")
    val popularity: Double,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "revenue")
    val revenue: Long,

    @Json(name = "runtime")
    val runtime: Int?,

    @Json(name = "status")
    val status: String,

    @Json(name = "tagline")
    val tagline: String?,

    @Json(name = "title")
    val title: String,

    @Json(name = "video")
    val video: Boolean,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "images")
    val images: Graphic,

    @Json(name = "videos")
    val videoResponse: VideoResponse
) {
    data class Collection(
        @Json(name = "id")
        val id: Int,

        @Json(name = "name")
        val name: String,

        @Json(name = "poster_path")
        val posterPath: String?,

        @Json(name = "backdrop_path")
        val backdropPath: String?
    )
}

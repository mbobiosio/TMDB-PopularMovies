package com.mbobiosio.popularmovies.data.mappers

import com.mbobiosio.popularmovies.data.local.entity.PopularMovie
import com.mbobiosio.popularmovies.data.remote.model.MovieResponse
import com.mbobiosio.popularmovies.domain.model.Movies

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */

fun MovieResponse.mapDataToPopularMovies(): Movies<PopularMovie> =
    with(this) {
        Movies(
            total = totalResults,
            page = page,
            movies = results.map {
                PopularMovie(
                    it.id,
                    it.title,
                    it.overview,
                    it.posterPath,
                    it.backdropPath,
                    it.releaseDate,
                    it.originalLanguage,
                    it.voteCount,
                    it.voteAverage
                )
            }
        )
    }

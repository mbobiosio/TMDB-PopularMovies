package com.mbobiosio.popularmovies.data.mappers

import com.mbobiosio.popularmovies.data.local.entity.MovieEntity
import com.mbobiosio.popularmovies.data.remote.model.movie.Movie

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
fun movieToEntity(movie: Movie): MovieEntity {
    return MovieEntity(
        id = movie.id,
        title = movie.title,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        genreIds = movie.genreIds,
        originalLanguage = movie.originalLanguage,
        popularity = movie.popularity,
        voteCount = movie.voteCount,
        hasVideo = movie.hasVideo,
        voteAverage = movie.voteAverage,
        isAdult = movie.adult
    )
}

fun entityToModel(movie: MovieEntity): Movie {
    return Movie(
        id = movie.id,
        title = movie.title,
        originalTitle = movie.originalTitle,
        overview = movie.overview,
        posterPath = movie.posterPath,
        backdropPath = movie.backdropPath,
        releaseDate = movie.releaseDate,
        genreIds = movie.genreIds,
        originalLanguage = movie.originalLanguage,
        popularity = movie.popularity,
        voteCount = movie.voteCount,
        hasVideo = movie.hasVideo,
        voteAverage = movie.voteAverage,
        adult = movie.isAdult
    )
}

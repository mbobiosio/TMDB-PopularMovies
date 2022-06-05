package com.mbobiosio.popularmovies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val remoteId: Int = 0,
    val title: String,
    val originalTitle: String,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val releaseDate: String?,
    val genreIds: List<Int>,
    val originalLanguage: String,
    val popularity: Double,
    val voteCount: Int,
    val hasVideo: Boolean,
    val voteAverage: Double,
    val isAdult: Boolean,
)

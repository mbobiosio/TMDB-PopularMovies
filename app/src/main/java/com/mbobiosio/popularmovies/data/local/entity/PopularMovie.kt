package com.mbobiosio.popularmovies.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Entity(tableName = "popular_movie")
@Parcelize
data class PopularMovie(
    val movieId: Long,
    @PrimaryKey
    val title: String,
    val overview: String,
    val poster: String,
    val backdrop: String?,
    val releaseDate: String?,
    val originalLanguage: String,
    val voteCount: Int,
    val voteAverage: Double
) : Parcelable

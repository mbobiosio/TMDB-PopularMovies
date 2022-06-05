package com.mbobiosio.popularmovies.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Entity(tableName = "remote_keys")
data class RemoteKeyEntity(
    @PrimaryKey
    val movieId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)

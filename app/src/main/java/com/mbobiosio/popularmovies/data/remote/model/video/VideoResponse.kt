package com.mbobiosio.popularmovies.data.remote.model.video

import com.squareup.moshi.Json

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
data class VideoResponse(
    @Json(name = "results")
    val results: List<Video>
)

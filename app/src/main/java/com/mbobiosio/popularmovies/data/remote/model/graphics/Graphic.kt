package com.mbobiosio.popularmovies.data.remote.model.graphics

import com.squareup.moshi.Json

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
data class Graphic(
    @Json(name = "id") val id: Int?,
    @Json(name = "backdrops")
    val backdrops: List<GraphicDetails>,
    @Json(name = "posters")
    val posters: List<GraphicDetails>
)

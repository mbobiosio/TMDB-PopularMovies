package com.mbobiosio.popularmovies.data.local

import androidx.room.TypeConverter
import com.mbobiosio.popularmovies.util.jsonToList
import com.mbobiosio.popularmovies.util.listToJson
import com.squareup.moshi.Moshi

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class TypeConverter {

    private val moshi = Moshi.Builder().build()

    @TypeConverter
    fun genreToString(genres: List<Int>): String =
        moshi.listToJson(genres)

    @TypeConverter
    fun stringToGenre(json: String): List<Int>? =
        moshi.jsonToList(json)
}

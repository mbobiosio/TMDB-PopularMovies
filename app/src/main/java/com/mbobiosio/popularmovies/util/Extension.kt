package com.mbobiosio.popularmovies.util

import android.text.format.DateUtils
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
/**
 * [Moshi] extension to transform a [List] to Json
 * */
inline fun <reified T> Moshi.listToJson(data: List<T>): String =
    adapter<List<T>>(
        Types.newParameterizedType(
            List::class.java, T::class.java
        )
    ).toJson(data)

/**
 * [Moshi] extension to transform a json object to a [List]
 * */
inline fun <reified T> Moshi.jsonToList(json: String): List<T>? =
    adapter<List<T>>(
        Types.newParameterizedType(
            List::class.java, T::class.java
        )
    ).fromJson(json)

/**
 * Using [DateUtils] to get timeAgo on provided date string.
 * */
fun formatDate(date: String): String {
    var timeAgo = ""
    if (date.isNotEmpty()) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formatDate = simpleDateFormat.parse(date)
        timeAgo = DateUtils.getRelativeTimeSpanString(formatDate?.time ?: 0).toString()
    }
    return timeAgo
}

/**
 * Kotlin extension extending so safely navigation to a navigation graph
 * Usage: @param [direction] is [NavDirections]
 * */
fun NavController.safeNavigate(direction: NavDirections) {
    currentDestination?.getAction(direction.actionId)?.run { navigate(direction) }
}

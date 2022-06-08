package com.mbobiosio.popularmovies.data.remote.api

import com.mbobiosio.popularmovies.data.remote.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String?,
        @Query("page") page: Int?
    ): MovieResponse
}

package com.mbobiosio.popularmovies.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbobiosio.popularmovies.data.local.entity.MovieEntity

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): PagingSource<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
}

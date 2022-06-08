package com.mbobiosio.popularmovies.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mbobiosio.popularmovies.data.local.entity.PopularMovie

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@Dao
interface PopularMovieDao {

    @Query("SELECT * FROM popular_movie")
    fun getMovies(): PagingSource<Int, PopularMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<PopularMovie>)

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAll()
}

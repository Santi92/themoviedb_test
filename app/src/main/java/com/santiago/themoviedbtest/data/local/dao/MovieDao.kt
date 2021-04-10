package com.santiago.themoviedbtest.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.santiago.themoviedbtest.data.local.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>): LongArray

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: MovieEntity): Long

    @Query("SELECT * FROM MovieEntity")
    fun getMoviesByPage(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM `MovieEntity` where id = :id")
    fun getMovieById(id: Long?): LiveData<MovieEntity>

}
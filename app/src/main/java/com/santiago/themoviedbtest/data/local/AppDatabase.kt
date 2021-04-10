package com.santiago.themoviedbtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.santiago.themoviedbtest.data.local.dao.MovieDao
import com.santiago.themoviedbtest.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}

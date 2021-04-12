package com.santiago.themoviedbtest.local


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import com.santiago.themoviedbtest.data.local.AppDatabase
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
abstract class AppDatabaseTest {
    protected var db: AppDatabase? = null



    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java).build()

    }

    @After
    fun closeDb() {
        db!!.close()
    }
}
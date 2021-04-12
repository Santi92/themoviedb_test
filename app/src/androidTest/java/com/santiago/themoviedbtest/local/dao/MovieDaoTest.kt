package com.santiago.themoviedbtest.local.dao


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.runner.AndroidJUnit4
import com.santiago.themoviedbtest.MockTestUtil
import com.santiago.themoviedbtest.data.local.entity.MovieEntity
import com.santiago.themoviedbtest.local.AppDatabaseTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieDaoTest : AppDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndReadMovieTest() {
        val movieEntities: MutableList<MovieEntity> = ArrayList()
        movieEntities.add(MockTestUtil.mockMovie())
        db!!.movieDao().insertMovies(movieEntities)
        val storedMovieEntities: List<MovieEntity> =  MockTestUtil.getValue(db!!.movieDao().getMoviesByPage())


        Assert.assertEquals("Godzilla vs. Kong", storedMovieEntities[0].title)
        Assert.assertEquals(399566, storedMovieEntities[0].id)
    }


    @Test
    fun getMovieByIdTest() {
        val movieEntities: MutableList<MovieEntity> = ArrayList()
        movieEntities.add(MockTestUtil.mockMovie())
        db!!.movieDao().insertMovies(movieEntities)
        val storedMovie =  MockTestUtil.getValue(db!!.movieDao().getMovieById(movieEntities.first().id))


        Assert.assertEquals("Godzilla vs. Kong", storedMovie.title)
        Assert.assertEquals(399566, storedMovie.id)
    }
}
package com.santiago.themoviedbtest.data.repository

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.observe

import com.santiago.themoviedbtest.AppExecutors
import com.santiago.themoviedbtest.data.NetworkBoundResource
import com.santiago.themoviedbtest.data.Resource
import com.santiago.themoviedbtest.data.local.dao.MovieDao
import com.santiago.themoviedbtest.data.local.entity.MovieEntity
import com.santiago.themoviedbtest.data.remote.ApiResponse
import com.santiago.themoviedbtest.data.remote.api.MovieApiService
import com.santiago.themoviedbtest.data.remote.model.MovieApiResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val appExecutors: AppExecutors,
    private val movieDao: MovieDao,
    private val movieApiService: MovieApiService
) {

    fun loadMoviesByType(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MovieApiResponse>(appExecutors) {
            override fun saveCallResult(item: MovieApiResponse) {
                movieDao.insertMovies(item.results)
            }

            override fun shouldFetch(data: List<MovieEntity>?)= data == null  || data.isEmpty()


            override fun loadFromDb(): LiveData<List<MovieEntity>> {
                return movieDao.getMoviesByPage()
            }

            override fun createCall(): LiveData<ApiResponse<MovieApiResponse>> {

               return movieApiService.fetchMoviesByType()
            }


        }.asLiveData()
    }

    fun fetchMovieDetails(movieId: Long): LiveData<Resource<MovieEntity>> {

        return object : NetworkBoundResource<MovieEntity, MovieEntity>(appExecutors) {
            override fun saveCallResult(item: MovieEntity) {
                val movieEntity: MovieEntity? = movieDao.getMovieById(movieId).value
                if(null == movieEntity){
                    movieDao.insertMovie(item)
                }
            }

            override fun shouldFetch(data: MovieEntity?)= data == null

            override fun loadFromDb(): LiveData<MovieEntity> {
                return movieDao.getMovieById(movieId)
            }

            override fun createCall(): LiveData<ApiResponse<MovieEntity>> {
                val id = movieId.toString()
                return movieApiService.fetchMovieDetail(id)
            }


        }.asLiveData()
    }

}
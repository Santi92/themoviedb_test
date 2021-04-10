package com.santiago.themoviedbtest.data.remote.api

import androidx.lifecycle.LiveData


import com.santiago.themoviedbtest.data.local.entity.MovieEntity
import com.santiago.themoviedbtest.data.remote.ApiResponse
import com.santiago.themoviedbtest.data.remote.model.MovieApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/popular?language=en-US&region=US&page=1")
    fun fetchMoviesByType(): LiveData<ApiResponse<MovieApiResponse>>

    @GET("/3/movie/{movieId}")
    fun fetchMovieDetail(@Path("movieId") movieId: String): LiveData<ApiResponse<MovieEntity>>
}
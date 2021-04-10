package com.santiago.themoviedbtest.features.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.santiago.themoviedbtest.data.Resource
import com.santiago.themoviedbtest.data.local.entity.MovieEntity
import com.santiago.themoviedbtest.data.repository.MovieRepository
import javax.inject.Inject


class MovieListViewModel @Inject constructor(
    movieRepository: MovieRepository) : ViewModel() {

    private val reload = MutableLiveData<Boolean>()

    val listMovie: LiveData<Resource<List<MovieEntity>>> = Transformations
        .switchMap(reload){
            movieRepository.loadMoviesByType()
        }

    fun reload(value: Boolean) {
        reload.value = value
    }
}
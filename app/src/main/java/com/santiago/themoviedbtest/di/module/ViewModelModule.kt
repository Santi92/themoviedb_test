package com.santiago.themoviedbtest.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.santiago.themoviedbtest.factory.ViewModelFactory
import com.santiago.themoviedbtest.features.detailMovie.MovieDetailViewModel
import com.santiago.themoviedbtest.features.movies.MovieListViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
internal abstract class ViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    protected abstract fun movieListViewModel(moviesListViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    protected abstract fun movieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
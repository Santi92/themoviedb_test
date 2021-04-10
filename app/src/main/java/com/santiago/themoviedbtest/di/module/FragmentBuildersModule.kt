package com.santiago.themoviedbtest.di.module

import com.santiago.themoviedbtest.features.detailMovie.MovieDetailFragment
import com.santiago.themoviedbtest.features.movies.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

   @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieDetailFragment(): MovieDetailFragment

}
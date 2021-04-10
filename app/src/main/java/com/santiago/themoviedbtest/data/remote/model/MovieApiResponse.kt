package com.santiago.themoviedbtest.data.remote.model

import com.santiago.themoviedbtest.data.local.entity.MovieEntity

data class MovieApiResponse(val page: Long,
                            val results: List<MovieEntity>,
                            val total_results: Long,
                            val total_pages: Long)
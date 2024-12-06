package com.firas.myapplication.common

import com.firas.myapplication.data.model.MovieDetail
import com.firas.myapplication.domain.model.MovieEntity

data class MovieState(
    val isLoading: Boolean = false,
    val movies: List<MovieEntity> = emptyList(),
    val error: String = "",
    val isPaginating: Boolean = false // New flag for pagination

)

data class MovieDetailState(
    val isLoading: Boolean = false,
    val moviesDetail: MovieDetail? = MovieDetail(),
    val error: String = ""
)


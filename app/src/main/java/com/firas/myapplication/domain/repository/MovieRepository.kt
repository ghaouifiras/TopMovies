package com.firas.myapplication.domain.repository

import com.firas.myapplication.data.model.MovieDetail
import com.firas.myapplication.data.model.ResultResponse
import com.firas.myapplication.domain.model.ResultResponseEntity

interface MovieRepository {

    suspend fun getMovies(page: Int): ResultResponseEntity
    suspend fun getMovieDetail(id: Int): MovieDetail
}
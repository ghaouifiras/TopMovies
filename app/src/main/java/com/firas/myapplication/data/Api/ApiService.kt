package com.firas.myapplication.data.Api

import com.firas.myapplication.data.model.MovieDetail
import com.firas.myapplication.data.model.ResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ResultResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
    ): MovieDetail


    @GET("tv/popular")
    suspend fun getTv(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): ResultResponse
}
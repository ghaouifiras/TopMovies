package com.firas.myapplication.data.repository

import com.firas.myapplication.data.Api.ApiService
import com.firas.myapplication.data.model.MovieDetail
import com.firas.myapplication.data.model.MovieResponse
import com.firas.myapplication.data.model.ResultResponse
import com.firas.myapplication.domain.model.MovieEntity
import com.firas.myapplication.domain.model.ResultResponseEntity
import com.firas.myapplication.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val apiService: ApiService) :
    MovieRepository {
    override suspend fun getMovies(page: Int): ResultResponseEntity {
        return apiService.getMovies(page = page).toResultEntity()
    }


    /* override suspend fun getMovies(): ResultResponseEntity {

         val resultResponse = apiService.getMovies()
         return resultResponse.toResultEntity()
     }*/

    override suspend fun getMovieDetail(id: Int): MovieDetail {
        return apiService.getMovieDetails(id)
    }


    fun ResultResponse.toResultEntity(): ResultResponseEntity {
        return ResultResponseEntity(
            page = this.page,
            results = this.results.map { it.toMovieEntity() },
            total_pages = this.total_pages,
            total_results = this.total_results

        )
    }


    fun MovieResponse.toMovieEntity(): MovieEntity {
        return MovieEntity(
            adult = this.adult,
            backdrop_path = this.backdrop_path,
            genre_ids = this.genre_ids,
            id = this.id,
            original_language = this.original_language,
            original_title = this.original_title,
            overview = this.overview,
            popularity = this.popularity,
            poster_path = this.poster_path,
            release_date = this.release_date,
            title = this.title,
            video = this.video,
            vote_average = this.vote_average,
            vote_count = this.vote_count
        )


    }


}
package com.firas.myapplication.domain.use_case

import com.firas.myapplication.common.Resource
import com.firas.myapplication.data.model.MovieDetail
import com.firas.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    fun getMovieDetail(id: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())

        try {
            val movieDetail = movieRepository.getMovieDetail(id)
            emit(Resource.Success(movieDetail))

        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }

    }
}
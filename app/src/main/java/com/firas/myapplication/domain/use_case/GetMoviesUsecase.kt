package com.firas.myapplication.domain.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.firas.myapplication.common.Resource
import com.firas.myapplication.data.MoviesPagingSource
import com.firas.myapplication.data.model.MovieDetail
import com.firas.myapplication.domain.model.MovieEntity
import com.firas.myapplication.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetMoviesUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    /* fun getMovies(): Flow<Resource<List<MovieEntity>>> = flow {

         emit(Resource.Loading())

         try {
             val listMovies = movieRepository.getMovies().results
             emit(Resource.Success(listMovies))

         } catch (e: HttpException) {
             emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
         } catch (e: IOException) {
             emit(Resource.Error("Couldn't reach server. Check your internet connection."))
         }


     }*/
    fun getMoviesPager(): Flow<PagingData<MovieEntity>> {
        return Pager(
            PagingConfig(
                pageSize = 20,
            )
        ) {
            MoviesPagingSource(movieRepository)
        }.flow
    }
}


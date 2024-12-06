package com.firas.myapplication.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.firas.myapplication.domain.model.MovieEntity
import com.firas.myapplication.domain.repository.MovieRepository

class MoviesPagingSource(
    private val repository: MovieRepository
) : PagingSource<Int, MovieEntity>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieEntity> {
        return try {
            val currentPage = params.key ?: 1
            Log.d("MoviesPagingSource", "Loading page $currentPage")
            // Fetch paginated data
            val response =
                repository.getMovies(page = currentPage) // Assuming it returns a paginated response
            val movies = response.results // Use your actual response field here

            LoadResult.Page(
                data = movies,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (movies.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, MovieEntity>): Int? {
        return state.anchorPosition?.let { position ->
            val closestPage = state.closestPageToPosition(position)
            closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
        }
    }


}
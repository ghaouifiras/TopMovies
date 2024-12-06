package com.firas.myapplication.presentation.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.firas.myapplication.common.MovieDetailState
import com.firas.myapplication.common.Resource
import com.firas.myapplication.domain.use_case.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val useCase: GetMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) :
    ViewModel() {


    private val _state = mutableStateOf(MovieDetailState())
    val state: State<MovieDetailState> = _state

    init {

        val movieId = savedStateHandle.get<String>("movieId")
        if (movieId != null) {
            getMovieDetail(movieId.toInt())
        }
    }

    fun getMovieDetail(id: Int) {
        useCase.getMovieDetail(id).onEach { movieDetail ->

            when (movieDetail) {
                is Resource.Error -> {
                    _state.value = MovieDetailState(
                        error = movieDetail.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = MovieDetailState(true)
                }

                is Resource.Success -> {
                    _state.value = MovieDetailState(moviesDetail = movieDetail.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}

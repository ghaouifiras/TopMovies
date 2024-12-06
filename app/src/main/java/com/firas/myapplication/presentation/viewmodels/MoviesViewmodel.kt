package com.firas.myapplication.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.firas.myapplication.domain.model.MovieEntity
import com.firas.myapplication.domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(useCase: GetMoviesUseCase) : ViewModel() {

    val moviesFlow: Flow<PagingData<MovieEntity>> =
        useCase.getMoviesPager().cachedIn(viewModelScope)

}
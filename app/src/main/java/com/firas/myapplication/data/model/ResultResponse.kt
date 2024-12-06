package com.firas.myapplication.data.model

data class ResultResponse(
    val page: Int,
    val results: List<MovieResponse>,
    val total_pages: Int,
    val total_results: Int
)
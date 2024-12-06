package com.firas.myapplication.domain.model

data class ResultResponseEntity(
    val page: Int,
    val results: List<MovieEntity>,
    val total_pages: Int,
    val total_results: Int
) {
}
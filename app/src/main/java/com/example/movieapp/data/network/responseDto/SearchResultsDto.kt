package com.example.movieapp.data.network.responseDto

data class SearchResultsDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)
package com.example.movieapp.domain.repository

import com.example.movieapp.data.network.responseDto.MovieDetailsDto
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.data.network.responseDto.SearchResultsDto

interface MovieRepository {
    suspend fun getMovieRecommendations(type: String): MovieRecommendationsDto

    suspend fun getSearchResults(query: String): SearchResultsDto

    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto
}
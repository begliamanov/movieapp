package com.example.movieapp.domain.repository

import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto

interface MovieRepository {
    suspend fun getMovieRecommendations(type: String): MovieRecommendationsDto
}
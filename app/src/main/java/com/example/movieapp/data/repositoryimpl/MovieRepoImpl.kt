package com.example.movieapp.data.repositoryimpl

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.network.api.MovieApi
import com.example.movieapp.data.network.responseDto.MovieDetailsDto
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.data.network.responseDto.SearchResultsDto
import com.example.movieapp.domain.repository.MovieRepo
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val api: MovieApi,
) : MovieRepo {
    override suspend fun getMovieRecommendations(type: String): MovieRecommendationsDto {
        return api.getMovieRecommendations(
            recommendationType = type,
            apiKey = BuildConfig.API_KEY
        )
    }

    override suspend fun getSearchResults(query: String): SearchResultsDto {
        return api.getSearchResults(
            apiKey = BuildConfig.API_KEY,
            query = query
        )
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return api.getMovieDetails(
            apiKey = BuildConfig.API_KEY,
            movieId = movieId
        )
    }
}
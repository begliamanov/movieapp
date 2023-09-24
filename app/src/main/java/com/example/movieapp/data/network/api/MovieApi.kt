package com.example.movieapp.data.network.api

import com.example.movieapp.data.network.responseDto.MovieDetailsDto
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.data.network.responseDto.SearchResultsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/{recommendation_type}")
    suspend fun getMovieRecommendations(
        @Path("recommendation_type") recommendationType: String,
        @Query("api_key") apiKey: String,
    ): MovieRecommendationsDto

    @GET("search/movie")
    suspend fun getSearchResults(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): SearchResultsDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
    ): MovieDetailsDto
}

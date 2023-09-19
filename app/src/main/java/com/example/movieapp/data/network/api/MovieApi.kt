package com.example.movieapp.data.network.api

import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("/movie/{recommendation_type}")
    suspend fun getMovieRecommendations(
        @Path("recommendation_type") recommendationType: String,
        @Query("api_key") apiKey: String,
    ): Result<MovieRecommendationsDto>
}

package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepo

class GetMovieRecommendationUseCase(private val movieRepo: MovieRepo) {
    suspend operator fun invoke(type: String) = movieRepo.getMovieRecommendations(type)

}
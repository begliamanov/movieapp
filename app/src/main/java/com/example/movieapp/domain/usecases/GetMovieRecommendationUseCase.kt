package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepository

class GetMovieRecommendationUseCase(private val movieRepository: MovieRepository) {
    operator fun invoke(type: String) = movieRepository.getMovieRecommendations(type)

}
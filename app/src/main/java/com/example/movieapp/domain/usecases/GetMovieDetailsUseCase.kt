package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(movieId: Int) = movieRepository.getMovieDetails(movieId)
}
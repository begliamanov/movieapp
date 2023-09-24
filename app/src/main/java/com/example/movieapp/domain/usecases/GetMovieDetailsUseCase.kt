package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepo

class GetMovieDetailsUseCase(private val movieRepo: MovieRepo) {
    suspend operator fun invoke(movieId: Int) = movieRepo.getMovieDetails(movieId)
}
package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepository

class GetSearchResultsUseCase(private val movieRepository: MovieRepository) {
    suspend operator fun invoke(query: String) = movieRepository.getSearchResults(query)
}
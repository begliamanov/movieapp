package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepo

class GetSearchResultsUseCase(private val movieRepo: MovieRepo) {
    suspend operator fun invoke(query: String) = movieRepo.getSearchResults(query)
}
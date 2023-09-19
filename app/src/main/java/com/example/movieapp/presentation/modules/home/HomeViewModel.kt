package com.example.movieapp.presentation.modules.home

import androidx.lifecycle.ViewModel
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.GetMovieRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    val useCase = GetMovieRecommendationUseCase(movieRepository)

}
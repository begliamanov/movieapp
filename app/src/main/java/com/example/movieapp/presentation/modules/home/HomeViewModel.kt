package com.example.movieapp.presentation.modules.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto.Movie
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.GetMovieRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {

    val getMovieRecommendationUseCase = GetMovieRecommendationUseCase(movieRepository)

    private val _movieState = MutableStateFlow(HomeState())
    val movieState = _movieState.asStateFlow()

    init {
        getMovieRecommendations("")
    }

    fun getMovieRecommendations(type: String) = viewModelScope.launch {
        val result = getMovieRecommendationUseCase(type)
        _movieState.update { _movieState.value.copy(movies = result.results) }
    }
}

data class HomeState(
    val isLoading: Boolean = false,
    val movies: List<Movie> = emptyList()
)
package com.example.movieapp.presentation.modules.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieDto
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.repository.StorageInterface
import com.example.movieapp.domain.usecases.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.GetMovieRecommendationUseCase
import com.example.movieapp.domain.usecases.ToggleMovieLikeDislikeUseCase
import com.example.movieapp.presentation.common.RecommendationType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository,
    storageInterface: StorageInterface
) : ViewModel() {

    val getMovieRecommendationUseCase = GetMovieRecommendationUseCase(movieRepository)
    val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(storageInterface)
    val toggleMovieLikeDislikeUseCase = ToggleMovieLikeDislikeUseCase(storageInterface)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()
    fun getMovieRecommendations() = viewModelScope.launch {
        val type = _state.value.recommendationType
        type?.let {
            val result = getMovieRecommendationUseCase(it.type)
            val favoriteMovies = getFavoriteMoviesUseCase()?.movies ?: emptyList()

            _state.update {
                _state.value.copy(
                    movies = result.results,
                    favoriteMovies = favoriteMovies
                )
            }
        }
    }

    fun onUpdateRecommendationType(type: RecommendationType) {
        _state.update { _state.value.copy(recommendationType = type) }
    }

    fun onLikeClickAction(movie: MovieDto) {
        toggleMovieLikeDislikeUseCase(movie)
        getMovieRecommendations()
    }


}

data class HomeState(
    val isLoading: Boolean = false,
    val recommendationType: RecommendationType? = null,
    val movies: List<MovieDto> = emptyList(),
    val favoriteMovies: List<MovieDto> = emptyList()
)
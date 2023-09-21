package com.example.movieapp.presentation.modules.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.domain.repository.StorageInterface
import com.example.movieapp.domain.usecases.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.ToggleMovieLikeDislikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    storageInterface: StorageInterface
) : ViewModel() {
    val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(storageInterface)
    val toggleMovieLikeDislikeUseCase = ToggleMovieLikeDislikeUseCase(storageInterface)

    private val _favoriteState = MutableStateFlow(FavoritesState())
    val favoriteState = _favoriteState.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() = viewModelScope.launch {
        val favoriteMovies = getFavoriteMoviesUseCase()?.movies ?: emptyList()
        _favoriteState.update { _favoriteState.value.copy(favoriteMovies = favoriteMovies) }
    }

    fun onLikeClickAction(movie: MovieRecommendationsDto.Movie) {
        toggleMovieLikeDislikeUseCase(movie)
        getFavorites()
    }
}

data class FavoritesState(
    val favoriteMovies: List<MovieRecommendationsDto.Movie> = emptyList()
)
package com.example.movieapp.presentation.modules.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieDto
import com.example.movieapp.domain.repository.StorageRepo
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
    storageRepo: StorageRepo
) : ViewModel() {
    val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(storageRepo)
    val toggleMovieLikeDislikeUseCase = ToggleMovieLikeDislikeUseCase(storageRepo)

    private val _state = MutableStateFlow(FavoritesState())
    val state = _state.asStateFlow()

    init {
        getFavorites()
    }

    private fun getFavorites() = viewModelScope.launch {
        val favoriteMovies = getFavoriteMoviesUseCase()?.movies ?: emptyList()
        _state.update { _state.value.copy(favoriteMovies = favoriteMovies) }
    }

    fun onLikeClickAction(movie: MovieDto) {
        toggleMovieLikeDislikeUseCase(movie)
        getFavorites()
    }

    fun isFavorite(movieId: Int) = toggleMovieLikeDislikeUseCase.isMovieFavorite(movieId)
}

data class FavoritesState(
    val favoriteMovies: List<MovieDto> = emptyList()
)
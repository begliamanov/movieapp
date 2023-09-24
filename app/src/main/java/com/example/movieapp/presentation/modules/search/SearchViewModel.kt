package com.example.movieapp.presentation.modules.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieDto
import com.example.movieapp.domain.repository.MovieRepo
import com.example.movieapp.domain.repository.StorageRepo
import com.example.movieapp.domain.usecases.GetFavoriteMoviesUseCase
import com.example.movieapp.domain.usecases.GetSearchResultsUseCase
import com.example.movieapp.domain.usecases.ToggleMovieLikeDislikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    movieRepo: MovieRepo,
    storageRepo: StorageRepo
) : ViewModel() {
    val getSearchResultsUseCase = GetSearchResultsUseCase(movieRepo)
    val getFavoriteMoviesUseCase = GetFavoriteMoviesUseCase(storageRepo)
    val toggleMovieLikeDislikeUseCase = ToggleMovieLikeDislikeUseCase(storageRepo)

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun onUpdateSearchText(text: String) {
        _searchState.update { _searchState.value.copy(searchText = text) }
    }

    fun onSearchClicked(query: String) = viewModelScope.launch {
        val movies = getSearchResultsUseCase(query).results
        val favoriteMovies = getFavoriteMoviesUseCase()?.movies ?: emptyList()
        _searchState.update {
            _searchState.value.copy(
                movies = movies,
                favoriteMovies = favoriteMovies
            )
        }
    }

    fun onLikeClickAction(movie: MovieDto) {
        toggleMovieLikeDislikeUseCase(movie)
        val favoriteMovies = getFavoriteMoviesUseCase()?.movies ?: emptyList()
        _searchState.update { _searchState.value.copy(favoriteMovies = favoriteMovies) }
    }

    fun isFavoriteMovie(movieId: Int) = toggleMovieLikeDislikeUseCase.isMovieFavorite(movieId)
}

data class SearchState(
    val isLoading: Boolean = false,
    val searchText: String = "",
    val movies: List<MovieDto> = emptyList(),
    val favoriteMovies: List<MovieDto> = emptyList()
)
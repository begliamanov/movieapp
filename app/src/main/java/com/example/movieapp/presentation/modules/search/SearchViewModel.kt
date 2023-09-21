package com.example.movieapp.presentation.modules.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.GetMovieRecommendationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    movieRepository: MovieRepository
) : ViewModel() {
    val getMovieRecommendationUseCase = GetMovieRecommendationUseCase(movieRepository)

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun onUpdateSearchText(text: String) {
        _searchState.update { _searchState.value.copy(searchText = text) }
    }

    fun onSearchClicked() = viewModelScope.launch {
        val movies = getMovieRecommendationUseCase("popular").results
            .filter { it.title.lowercase().contains(_searchState.value.searchText.lowercase()) }
        _searchState.update { _searchState.value.copy(movies = movies) }

    }
}

data class SearchState(
    val isLoading: Boolean = false,
    val searchText: String = "",
    val movies: List<MovieRecommendationsDto.Movie> = emptyList()
)
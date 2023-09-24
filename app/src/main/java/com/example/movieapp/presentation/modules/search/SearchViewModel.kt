package com.example.movieapp.presentation.modules.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieDto
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.usecases.GetSearchResultsUseCase
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
    val getSearchResultsUseCase = GetSearchResultsUseCase(movieRepository)

    private val _searchState = MutableStateFlow(SearchState())
    val searchState = _searchState.asStateFlow()

    fun onUpdateSearchText(text: String) {
        _searchState.update { _searchState.value.copy(searchText = text) }
    }

    fun onSearchClicked(query: String) = viewModelScope.launch {
        val movies = getSearchResultsUseCase(query).results
        _searchState.update { _searchState.value.copy(movies = movies) }

    }
}

data class SearchState(
    val isLoading: Boolean = false,
    val searchText: String = "",
    val movies: List<MovieDto> = emptyList()
)
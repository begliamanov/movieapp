package com.example.movieapp.presentation.modules.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.network.responseDto.MovieDetailsDto
import com.example.movieapp.domain.repository.MovieRepository
import com.example.movieapp.domain.repository.StorageInterface
import com.example.movieapp.domain.usecases.GetMovieDetailsUseCase
import com.example.movieapp.domain.usecases.ToggleMovieLikeDislikeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoveDetailsViewModel @Inject constructor(
    movieRepository: MovieRepository,
    storageInterface: StorageInterface,
) : ViewModel() {

    private val getMovieDetailsUseCase = GetMovieDetailsUseCase(movieRepository)
    val toggleMovieLikeDislikeUseCase = ToggleMovieLikeDislikeUseCase(storageInterface)


    private val _state = MutableStateFlow(MovieDetailsState())
    val state = _state.asStateFlow()
    fun getMovieDetails(movieId: Int) = viewModelScope.launch {
        val movieDetails = getMovieDetailsUseCase(movieId)
        val isFavorite = isFavorite(movieId)
        _state.update {
            _state.value.copy(
                movieDetails = movieDetails,
                isFavorite = isFavorite
            )
        }
    }

    fun onLikeClickAction(movieDetails: MovieDetailsDto) {
        toggleMovieLikeDislikeUseCase(movieDetails)
        val isFavorite = isFavorite(movieDetails.id)
        _state.update { _state.value.copy(isFavorite = isFavorite) }
    }


    private fun isFavorite(movieId: Int) = toggleMovieLikeDislikeUseCase.isMovieFavorite(movieId)


}

data class MovieDetailsState(
    val isLoading: Boolean = false,
    val movieDetails: MovieDetailsDto? = null,
    val isFavorite: Boolean? = null,
)
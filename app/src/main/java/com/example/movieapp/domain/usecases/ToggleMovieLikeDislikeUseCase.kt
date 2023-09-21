package com.example.movieapp.domain.usecases

import com.example.movieapp.data.localstorage.LocalStorageKeys.FavoriteMoviesKey
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto.Movie
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto.MovieList
import com.example.movieapp.domain.repository.StorageInterface

class ToggleMovieLikeDislikeUseCase(private val storageInterface: StorageInterface) {
    operator fun invoke(movie: Movie) {
        if (isMovieFavorite(movie))
            removeFromFavorites(movie)
        else
            addToFavorites(movie)


    }

    private fun isMovieFavorite(movie: Movie): Boolean {
        val favMovies = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)?.movies
        return favMovies?.contains(movie) == true
    }

    private fun addToFavorites(movie: Movie) {
        val favMovies = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)?.movies
            ?: mutableListOf()
        favMovies.add(movie)
        storageInterface.setObject(FavoriteMoviesKey, MovieList(favMovies))
    }

    private fun removeFromFavorites(movie: Movie) {
        val favMovies = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)?.movies
            ?: mutableListOf()
        favMovies.remove(movie)
        storageInterface.setObject(FavoriteMoviesKey, MovieList(favMovies))
    }


}
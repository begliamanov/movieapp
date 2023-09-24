package com.example.movieapp.domain.usecases

import com.example.movieapp.data.localstorage.LocalStorageKeys.FavoriteMoviesKey
import com.example.movieapp.data.network.responseDto.MovieDto
import com.example.movieapp.data.network.responseDto.MovieList
import com.example.movieapp.domain.repository.StorageInterface

class ToggleMovieLikeDislikeUseCase(private val storageInterface: StorageInterface) {
    operator fun invoke(movie: MovieDto) {
        if (isMovieFavorite(movie))
            removeFromFavorites(movie)
        else
            addToFavorites(movie)


    }

    private fun isMovieFavorite(movie: MovieDto): Boolean {
        val favMovies = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)?.movies
        return favMovies?.contains(movie) == true
    }

    private fun addToFavorites(movie: MovieDto) {
        val favMovies = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)?.movies
            ?: mutableListOf()
        favMovies.add(movie)
        storageInterface.setObject(FavoriteMoviesKey, MovieList(favMovies))
    }

    private fun removeFromFavorites(movie: MovieDto) {
        val favMovies = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)?.movies
            ?: mutableListOf()
        favMovies.remove(movie)
        storageInterface.setObject(FavoriteMoviesKey, MovieList(favMovies))
    }


}
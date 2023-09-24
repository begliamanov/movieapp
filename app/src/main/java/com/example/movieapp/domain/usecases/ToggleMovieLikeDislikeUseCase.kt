package com.example.movieapp.domain.usecases

import com.example.movieapp.data.localstorage.LocalStorageKeys.FavoriteMoviesKey
import com.example.movieapp.data.network.responseDto.MovieDetailsDto
import com.example.movieapp.data.network.responseDto.MovieDto
import com.example.movieapp.data.network.responseDto.MovieList
import com.example.movieapp.domain.repository.StorageInterface

class ToggleMovieLikeDislikeUseCase(private val storageInterface: StorageInterface) {
    operator fun invoke(movie: MovieDto) {
        if (isMovieFavorite(movie.id))
            removeFromFavorites(movie)
        else
            addToFavorites(movie)
    }

    operator fun invoke(movieDetails: MovieDetailsDto) {
        val movie = movieDetails.convertToMovieDto()
        if (isMovieFavorite(movie.id))
            removeFromFavorites(movie)
        else
            addToFavorites(movie)
    }

    private fun MovieDetailsDto.convertToMovieDto() = MovieDto(
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        genre_ids = this.genres.map { it.id },
        id = this.id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
    )

    fun isMovieFavorite(movieId: Int): Boolean {
        val favMoviesId = storageInterface.getObject(
            FavoriteMoviesKey,
            MovieList::class.java
        )?.movies?.map { it.id }
        return favMoviesId?.contains(movieId) == true
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
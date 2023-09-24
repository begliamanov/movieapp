package com.example.movieapp.domain.usecases

import com.example.movieapp.data.localstorage.LocalStorageKeys.FavoriteMoviesKey
import com.example.movieapp.data.network.responseDto.MovieList
import com.example.movieapp.domain.repository.StorageInterface

class GetFavoriteMoviesUseCase(private val storageInterface: StorageInterface) {
    operator fun invoke() = storageInterface.getObject(FavoriteMoviesKey, MovieList::class.java)
}
package com.example.movieapp.domain.usecases

import com.example.movieapp.data.localstorage.LocalStorageKeys.FavoriteMoviesKey
import com.example.movieapp.data.network.responseDto.MovieList
import com.example.movieapp.domain.repository.StorageRepo

class GetFavoriteMoviesUseCase(private val storageRepo: StorageRepo) {
    operator fun invoke() = storageRepo.getObject(FavoriteMoviesKey, MovieList::class.java)
}
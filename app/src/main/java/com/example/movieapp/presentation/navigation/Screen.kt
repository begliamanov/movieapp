package com.example.movieapp.presentation.navigation

sealed class Screen(val route: String) {
    object FavoriteScreen : Screen("favorite_screen")
    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
    object MovieDetailsScreen : Screen("movie_details_screen")
}
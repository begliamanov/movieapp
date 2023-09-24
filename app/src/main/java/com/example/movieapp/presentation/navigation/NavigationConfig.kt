package com.example.movieapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.movieapp.presentation.modules.favorites.FavoritesPage
import com.example.movieapp.presentation.modules.home.HomePage
import com.example.movieapp.presentation.modules.moviedetails.MovieDetailsPage
import com.example.movieapp.presentation.modules.search.SearchPage


@Composable
fun NavigationConfig(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.FavoriteScreen.route) {
        composable(route = Screen.FavoriteScreen.route) { FavoritesPage(navController) }
        composable(route = Screen.HomeScreen.route) { HomePage(navController) }
        composable(route = Screen.SearchScreen.route) { SearchPage(navController) }
        composable(
            route = Screen.MovieDetailsScreen.route + "/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { MovieDetailsPage(navController, it.arguments?.getInt("movieId")) }
    }
}
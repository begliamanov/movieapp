package com.example.movieapp.presentation.modules.favorites

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun FavoritesPage(navController: NavController) {
    Text(text = "Favorite", fontSize = 50.sp)
}
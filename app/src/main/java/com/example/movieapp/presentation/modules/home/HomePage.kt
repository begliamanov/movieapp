package com.example.movieapp.presentation.modules.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomePage(navController: NavController) {
    Text(text = "Home", fontSize = 50.sp)

}
package com.example.movieapp.presentation.modules.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Text(text = "Home", fontSize = 50.sp)

}
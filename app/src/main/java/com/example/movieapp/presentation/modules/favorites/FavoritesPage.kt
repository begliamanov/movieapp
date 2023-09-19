package com.example.movieapp.presentation.modules.favorites

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.presentation.widgets.GenericTobAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesPage(navController: NavController) {
    Scaffold(
        topBar = {
            GenericTobAppBar(title = stringResource(R.string.favorites))
        },
        content = { padding ->

        })
}
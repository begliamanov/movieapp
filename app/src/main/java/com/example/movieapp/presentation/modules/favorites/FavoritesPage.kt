package com.example.movieapp.presentation.modules.favorites

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.R
import com.example.movieapp.presentation.common.DateUtils
import com.example.movieapp.presentation.modules.home.MovieItem
import com.example.movieapp.presentation.widgets.GenericTobAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesPage(
    navController: NavController,
    viewModel: FavoritesViewModel = hiltViewModel()
) {
    val state = viewModel.favoriteState.collectAsState()

    Scaffold(
        topBar = {
            GenericTobAppBar(title = stringResource(R.string.favorites))
        },
        content = { padding ->

            LazyVerticalGrid(
                modifier = Modifier.padding(padding),
                columns = GridCells.Fixed(2),
                content = {
                    items(state.value.favoriteMovies) { movie ->
                        MovieItem(imageUrl = movie.backdrop_path,
                            releaseYear = DateUtils.getYearFromStringDate(movie.release_date),
                            isFavorite = state.value.favoriteMovies.contains(movie),
                            averageRating = movie.vote_average,
                            onClick = { viewModel.onLikeClickAction(movie) })
                    }
                })
        })
}
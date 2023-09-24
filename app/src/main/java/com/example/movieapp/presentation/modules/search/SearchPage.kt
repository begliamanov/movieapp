package com.example.movieapp.presentation.modules.search

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.common.DateUtils
import com.example.movieapp.presentation.navigation.Screen
import com.example.movieapp.presentation.widgets.MovieItem
import com.example.movieapp.presentation.widgets.SearchAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchPage(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.searchState.collectAsState()

    Scaffold(topBar = {
        SearchAppBar(
            text = state.value.searchText,
            onTextChange = { viewModel.onUpdateSearchText(it) },
            onCloseClicked = { /*TODO*/ },
            onSearchClicked = { viewModel.onSearchClicked(state.value.searchText) })
    }, content = { padding ->

        LazyVerticalGrid(
            modifier = Modifier.padding(padding),
            columns = GridCells.Fixed(2),
            content = {
                items(state.value.movies) { movie ->
                    MovieItem(imageUrl = movie.poster_path,
                        releaseYear = DateUtils.getYearFromStringDate(movie.release_date),
                        isFavorite = viewModel.isFavoriteMovie(movie.id),
                        averageRating = movie.vote_average,
                        onClick = { navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.id}") },
                        onDoubleTap = { viewModel.onLikeClickAction(movie) })
                }
            })

    })

}
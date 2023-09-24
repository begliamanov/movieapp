package com.example.movieapp.presentation.modules.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.presentation.common.DateUtils.getYearFromStringDate
import com.example.movieapp.presentation.common.RecommendationType
import com.example.movieapp.presentation.navigation.Screen
import com.example.movieapp.presentation.widgets.GenericTabRow
import com.example.movieapp.presentation.widgets.GenericTobAppBar
import com.example.movieapp.presentation.widgets.MovieItem
import com.example.movieapp.presentation.widgets.TabRowElement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.collectAsState()

    Scaffold(topBar = {
        GenericTobAppBar(title = "Home")
    }, content = { padding ->
        var currentTabIndex by remember { mutableStateOf(0) }.also {
            val type = RecommendationType.values().first()
            viewModel.onUpdateRecommendationType(type)
            viewModel.getMovieRecommendations()
        }


        val tabRowElements = RecommendationType.values().map {
            TabRowElement(
                titleId = it.resValue,
                onClick = { })
        }
        Column {
            GenericTabRow(
                modifier = Modifier.padding(padding),
                tabRowElements = tabRowElements,
                currentTabIndex = currentTabIndex
            ) {
                currentTabIndex = it
                val type = RecommendationType.values()[it]
                viewModel.onUpdateRecommendationType(type)
                viewModel.getMovieRecommendations()
            }
            LazyVerticalGrid(
                modifier = Modifier.padding(),
                columns = GridCells.Fixed(2),
                content = {
                    items(state.value.movies) { movie ->
                        MovieItem(imageUrl = movie.poster_path,
                            releaseYear = getYearFromStringDate(movie.release_date),
                            isFavorite = state.value.favoriteMovies.contains(movie),
                            averageRating = movie.vote_average,
                            onClick = { navController.navigate(Screen.MovieDetailsScreen.route + "/${movie.id}") },
                            onDoubleTap = { viewModel.onLikeClickAction(movie) })
                    }
                })
        }

    })

}


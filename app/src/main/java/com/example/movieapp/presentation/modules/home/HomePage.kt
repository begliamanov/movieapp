package com.example.movieapp.presentation.modules.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.common.DateUtils.getYearFromStringDate
import com.example.movieapp.presentation.common.RecommendationType
import com.example.movieapp.presentation.navigation.Screen
import com.example.movieapp.presentation.widgets.GenericTabRow
import com.example.movieapp.presentation.widgets.GenericTobAppBar
import com.example.movieapp.presentation.widgets.TabRowElement

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController, viewModel: HomeViewModel = hiltViewModel()
) {

    val state = viewModel.movieState.collectAsState()

    Scaffold(topBar = {
        GenericTobAppBar(title = "Home")
    }, content = { padding ->
        var currentTabIndex by remember { mutableStateOf(0) }


        val tabRowElements = RecommendationType.values().map {
            TabRowElement(
                titleId = it.resValue,
                onClick = { viewModel.getMovieRecommendations(it.type) })
        }
        Column {
            GenericTabRow(
                modifier = Modifier.padding(padding),
                tabRowElements = tabRowElements,
                currentTabIndex = currentTabIndex
            ) { currentTabIndex = it }
            LazyVerticalGrid(modifier = Modifier.padding(horizontal = 8.dp),
                columns = GridCells.Fixed(2),
                content = {
                    items(state.value.movies) { movie ->
                        MovieItem(imageUrl = movie.backdrop_path,
                            releaseYear = getYearFromStringDate(movie.release_date),
                            isFavorite = false,
                            averageRating = movie.vote_average,
                            onClick = { navController.navigate(Screen.SearchScreen.route) })
                    }
                })
        }

    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieItem(
    imageUrl: String,
    releaseYear: String,
    isFavorite: Boolean,
    averageRating: Double,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .height(300.dp)
            .width(164.dp)
            .background(Color.White),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Column {
            AsyncImage(
                modifier = Modifier.weight(1f),
                model = "https://image.tmdb.org/t/p/w500/$imageUrl",
                contentScale = ContentScale.FillHeight,
                contentDescription = null,
            )
            Row(
                modifier = Modifier
                    .height(40.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    text = releaseYear,
                    fontSize = 13.sp
                )
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_black_18),
                        contentDescription = null
                    )
                    Text(text = averageRating.toString(), fontSize = 13.sp)
                }
                Image(
                    modifier = Modifier.padding(end = 16.dp),
                    painter = painterResource(
                        id = if (isFavorite) R.drawable.baseline_favorite_black_18
                        else R.drawable.baseline_favorite_border_black_18
                    ), contentDescription = null
                )
            }
        }
    }
}

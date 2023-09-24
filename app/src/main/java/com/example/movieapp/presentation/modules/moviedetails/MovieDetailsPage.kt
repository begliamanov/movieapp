package com.example.movieapp.presentation.modules.moviedetails

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.common.Config.IMAGE_BASE_URL
import com.example.movieapp.presentation.common.DateUtils.getYearFromStringDate
import com.example.movieapp.presentation.common.Decimalutils.roundOneDecimal

@Composable
fun MovieDetailsPage(
    navController: NavController,
    movieId: Int? = null,
    viewModel: MoveDetailsViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = movieId) {
        movieId?.let {
            viewModel.getMovieDetails(it)
        }
    }
    val state = viewModel.state.collectAsState()
    val movieDetails = state.value.movieDetails
    movieDetails?.let { movie ->
        Column {
            Box() {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(),
                    model = "$IMAGE_BASE_URL${movie.backdrop_path}",
                    contentScale = ContentScale.FillWidth,
                    contentDescription = null,
                )
                IconButton(
                    modifier = Modifier.padding(all = 8.dp),
                    onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        tint = Color.White,
                        contentDescription = null
                    )
                }
            }
            Row(Modifier.padding(all = 16.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .height(250.dp)
                        .width(164.dp)
                        .padding(end = 16.dp),
                    model = "$IMAGE_BASE_URL${movie.poster_path}",
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null,
                )
                Column {
                    Text(text = movie.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = movie.tagline,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = stringResource(
                            R.string.released_in_s,
                            getYearFromStringDate(movie.release_date)
                        ),
                        fontSize = 13.sp
                    )
                    Row(modifier = Modifier.padding(top = 8.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.baseline_star_black_18),
                            contentDescription = null
                        )

                        Text(
                            text = stringResource(
                                R.string.voteAverageOutOf10,
                                movie.vote_average.roundOneDecimal()
                            ), fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "(${movie.vote_count})", fontSize = 12.sp)
                    }
                    LazyRow(
                        modifier = Modifier.padding(top = 16.dp),
                        content = {
                            items(movie.genres.take(2)) { genre ->
                                Card(
                                    shape = RoundedCornerShape(50),
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(
                                            0xFFe4e7eb
                                        )
                                    )
                                ) {
                                    Text(
                                        modifier = Modifier.padding(
                                            horizontal = 12.dp,
                                            vertical = 4.dp
                                        ), text = genre.name
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(70.dp))
                    Row {
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(top = 0.dp)
                                .clickable { viewModel.onLikeClickAction(movie) },
                            painter = painterResource(
                                id = if (state.value.isFavorite == true) R.drawable.baseline_favorite_black_18
                                else R.drawable.baseline_favorite_border_black_18
                            ), contentDescription = null
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Divider()
                Text(
                    modifier = Modifier.padding(top = 24.dp, bottom = 16.dp),
                    text = stringResource(R.string.overview),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = movie.overview, fontSize = 16.sp)
            }
        }
    }
}
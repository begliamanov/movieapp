package com.example.movieapp.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieapp.R

@Composable
fun MovieItem(
    imageUrl: String?,
    releaseYear: String,
    isFavorite: Boolean,
    averageRating: Double,
    onClick: () -> Unit,
    onDoubleTap: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(all = 8.dp)
            .height(300.dp)
            .width(164.dp)
            .background(Color.White)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onDoubleTap = { onDoubleTap() }
                )
            },
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
                model = imageUrl?.let { "https://image.tmdb.org/t/p/w500/$it" },
                contentScale = ContentScale.FillWidth,
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
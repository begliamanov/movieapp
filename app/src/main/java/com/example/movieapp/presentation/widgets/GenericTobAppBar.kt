package com.example.movieapp.presentation.widgets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTobAppBar(
    title: String,
    accessories: @Composable (() -> Unit)? = null,
) {
    TopAppBar(
        title = {
            Text(text = title, color = Color.White, fontSize = 20.sp)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
        actions = { accessories?.invoke() }
    )
}
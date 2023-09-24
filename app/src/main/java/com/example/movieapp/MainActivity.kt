package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.presentation.navigation.NavigationConfig
import com.example.movieapp.presentation.navigation.Screen
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

data class BottomNavigationItem(
    val title: String,
    val icon: Int,
    val screen: Screen
)


@AndroidEntryPoint
@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {

                val items = listOf(
                    BottomNavigationItem(
                        "Favorites",
                        R.drawable.baseline_favorite_black_24,
                        Screen.FavoriteScreen
                    ),
                    BottomNavigationItem(
                        "Home",
                        R.drawable.baseline_movie_black_24,
                        Screen.HomeScreen
                    ),
                    BottomNavigationItem(
                        "Search",
                        R.drawable.baseline_search_black_24,
                        Screen.SearchScreen
                    ),
                )

                val navController = rememberNavController()
                var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        bottomBar = {
                            val currentScreen = navBackStackEntry?.destination?.route
                            val movieDetailsScreen = Screen.MovieDetailsScreen.route + "/{movieId}"
                            AnimatedVisibility(
                                visible = currentScreen != movieDetailsScreen,
                                enter = fadeIn(),
                                exit = fadeOut()
                            ) {
                                NavigationBar(
                                    containerColor = Color.Black
                                ) {

                                    items.forEachIndexed { index, item ->
                                        NavigationBarItem(
                                            colors = NavigationBarItemDefaults.colors(
                                                indicatorColor = Color.Black
                                            ),
                                            selected = selectedItemIndex == index,
                                            onClick = {
                                                selectedItemIndex = index
                                                navController.navigate(item.screen.route)
                                            },
                                            icon = {
                                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Icon(
                                                        painter = painterResource(id = item.icon),
                                                        tint = Color.White,
                                                        contentDescription = null
                                                    )
                                                    AnimatedVisibility(visible = selectedItemIndex == index) {
                                                        Text(
                                                            text = item.title,
                                                            fontSize = 12.sp,
                                                            fontStyle = FontStyle.Normal,
                                                            color = Color.White
                                                        )
                                                    }
                                                }
                                            },
                                        )
                                    }
                                }
                            }
                        }
                    ) { padding ->
                        NavigationConfig(navController = navController)
                    }
                }
            }
        }
    }
}


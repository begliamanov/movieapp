package com.example.movieapp.presentation.common

import androidx.annotation.StringRes
import com.example.movieapp.R

enum class RecommendationType(val type: String, @StringRes val resValue: Int) {
    NOW_PLAYING("now_playing", R.string.nowPlaying),
    POPULAR("popular", R.string.popular),
    TOP_RATED("top_rated", R.string.topRated),
    UPCOMING("upcoming", R.string.upcoming),
}
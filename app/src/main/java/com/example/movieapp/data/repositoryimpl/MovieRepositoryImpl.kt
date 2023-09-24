package com.example.movieapp.data.repositoryimpl

import android.content.res.Resources
import androidx.annotation.RawRes
import com.example.movieapp.BuildConfig
import com.example.movieapp.data.network.api.MovieApi
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.data.network.responseDto.SearchResultsDto
import com.example.movieapp.domain.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi,
) : MovieRepository {
    override suspend fun getMovieRecommendations(type: String): MovieRecommendationsDto {
        return api.getMovieRecommendations(
            recommendationType = type,
            apiKey = BuildConfig.API_KEY
        )
    }

    override suspend fun getSearchResults(query: String): SearchResultsDto {
        return api.getSearchResults(
            apiKey = BuildConfig.API_KEY,
            query = query
        )
    }

}

inline fun <reified T> Resources.getJsonData(@RawRes resourceId: Int): T {
    val dataType = object : TypeToken<T>() {}.type
    val inputStream = openRawResource(resourceId)
    val reader = InputStreamReader(inputStream)

    return Gson().fromJson(reader.readText(), dataType)
}
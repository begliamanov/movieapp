package com.example.movieapp.data.repositoryimpl

import android.content.res.Resources
import androidx.annotation.RawRes
import com.example.movieapp.R
import com.example.movieapp.data.network.responseDto.MovieRecommendationsDto
import com.example.movieapp.domain.repository.MovieRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader
import javax.inject.Inject

class MovieRepositoryResImpl @Inject constructor(
    private val resources: Resources
) : MovieRepository {
    override fun getMovieRecommendations(type: String): MovieRecommendationsDto {
        return resources.getJsonData(R.raw.get_recommendation_response)
    }

}

inline fun <reified T> Resources.getJsonData(@RawRes resourceId: Int): T {
    val dataType = object : TypeToken<T>() {}.type
    val inputStream = openRawResource(resourceId)
    val reader = InputStreamReader(inputStream)

    return Gson().fromJson(reader.readText(), dataType)
}
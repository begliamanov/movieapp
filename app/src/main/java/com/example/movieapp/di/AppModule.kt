package com.example.movieapp.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.example.movieapp.data.repositoryimpl.MovieRepositoryResImpl
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMovieRecommendations(resources: Resources): MovieRepository {
        return MovieRepositoryResImpl(resources)
    }

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    fun provideResources(context: Context): Resources {
        return context.resources
    }
}
package com.example.tvmazemvvm.di

import android.content.Context
import com.example.tvmazemvvm.data.network.MoviesRemoteDtaSource
import com.example.tvmazemvvm.data.network.MoviesService
import com.example.tvmazemvvm.data.room.AppDatabase
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.tvmaze.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideGson() :Gson = GsonBuilder().create()

    @Provides
    fun provideMoviesService(retrofit: Retrofit):MoviesService =
        retrofit.create(MoviesService::class.java)

    @Singleton
    @Provides
    fun provideMoviesRemoteDataSource(moviesService: MoviesService) = MoviesRemoteDtaSource(moviesService)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext : Context) = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideMoviesDao(db:AppDatabase) = db.moviesDao()


}
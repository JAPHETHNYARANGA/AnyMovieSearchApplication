package com.example.tvmazemvvm.data.network

import com.example.tvmazemvvm.domain.models.Model
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("search/shows")
    suspend fun getAllMovies(@Query("q")movieName:String) : Response<Model>
}
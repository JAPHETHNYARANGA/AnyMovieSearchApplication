package com.example.tvmazemvvm.data.network

import javax.inject.Inject

class MoviesRemoteDtaSource @Inject constructor(
    private val moviesService: MoviesService
) : BaseDataSource(){
    suspend fun getMovies() = getResult { moviesService.getAllMovies("x-men") }
}
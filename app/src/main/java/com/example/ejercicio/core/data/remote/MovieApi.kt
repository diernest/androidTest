package com.example.ejercicio.core.data.remote.dto

import retrofit2.http.GET

interface MovieApi {
    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieDtoResponse



}
package com.example.ejercicio.core.data.remote.dto

import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    companion object{
        const val BASE_IMAGE = "https://image.tmdb.org/t/p/w500/"
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MovieDtoResponse

    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieDtoResponse

    @GET("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&include_adult=false")
    suspend fun getMoviesByYear(@Query("year") year:Int): MovieDtoResponse

    @GET("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&include_adult=false")
    suspend fun getMoviesByLanguage(@Query("with_original_language") language: String): MovieDtoResponse
}
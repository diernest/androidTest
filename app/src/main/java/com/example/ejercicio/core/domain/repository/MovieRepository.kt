package com.example.ejercicio.core.domain.repository

import com.example.ejercicio.core.domain.model.Movie

interface MovieRepository {
    suspend fun getUpcomingMovies(): Result<List<Movie>>
    suspend fun getPopularMovies(): Result<List<Movie>>
    suspend fun getMoviesByYear(year: Int): Result<List<Movie>>
    suspend fun getMoviesByLanguage(language: String) : Result<List<Movie>>
}
package com.example.ejercicio.core.domain.repository

import com.example.ejercicio.core.domain.model.Movie

interface MovieRepository {
    suspend fun getUpcomingMovies(): List<Movie>

}
package com.example.ejercicio.home.presentation

import com.example.ejercicio.core.domain.model.Movie

data class HomeState (
    val upcomingMovies : List<Movie> = emptyList(),
    val popularMovies : List<Movie> = emptyList(),
    val isLoading: Boolean = false
)
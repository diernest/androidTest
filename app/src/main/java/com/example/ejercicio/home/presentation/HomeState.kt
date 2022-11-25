package com.example.ejercicio.home.presentation

import com.example.ejercicio.core.domain.model.Movie
import com.example.ejercicio.home.presentation.components.FilterType

data class HomeState (
    val upcomingMovies : List<Movie> = emptyList(),
    val popularMovies : List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val selectedFilter: FilterType = FilterType.SPANISH,
    val filteredMovies: List<Movie> = emptyList()
)
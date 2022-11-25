package com.example.ejercicio.home.presentation

import com.example.ejercicio.core.domain.model.Movie

data class HomeState (
    val upcoming : List<Movie> = emptyList(),
    val isLoading: Boolean = false
)
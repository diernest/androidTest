package com.example.ejercicio.home.presentation.components

sealed class HomeEvent {
    data class ChangeFilter(val filterType: FilterType) : HomeEvent()
    data class OnMovieClick(val movieId: Int) : HomeEvent()
}
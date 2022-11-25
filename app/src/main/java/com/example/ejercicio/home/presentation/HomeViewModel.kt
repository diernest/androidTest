package com.example.ejercicio.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio.core.domain.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){
    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(isLoading = true)
        getUpcomingMovies()
        getPopularMovies()
    }

    private fun getPopularMovies(){
        viewModelScope.launch {
            repository.getPopularMovies().onSuccess {
                state = state.copy(
                    popularMovies = it
                )
            }.onFailure {

            }
            state = state.copy(isLoading = false)
        }
    }

    private fun getUpcomingMovies(){
        viewModelScope.launch {
            repository.getUpcomingMovies().onSuccess {
                state = state.copy(
                    upcomingMovies = it
                )
            }.onFailure {

            }
            state = state.copy(isLoading = false)
        }
    }
}
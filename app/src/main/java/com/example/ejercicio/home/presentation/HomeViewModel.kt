package com.example.ejercicio.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio.core.domain.repository.MovieRepository
import com.example.ejercicio.home.presentation.components.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel(){
    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            supervisorScope {
                val upcoming = launch { getUpcomingMovies() }
                val popular = launch { getPopularMovies() }
                listOf(upcoming, popular).forEach {
                    it.join()
                }
                state = state.copy(isLoading = false)
            }

        }
    }

    fun onEvent(event: HomeEvent) {
        when (event){
            is HomeEvent.ChangeFilter -> {
                state = state.copy(
                    selectedFilter =  event.filterType
                )
            }
            is HomeEvent.OnMovieClick -> {}
        }
    }

    private suspend fun getPopularMovies() {
            repository.getPopularMovies().onSuccess {
                state = state.copy(
                    popularMovies = it
                )
            }.onFailure {

            }
            state = state.copy(isLoading = false)
    }

    private suspend fun getUpcomingMovies(){
            repository.getUpcomingMovies().onSuccess {
                state = state.copy(
                    upcomingMovies = it
                )
            }.onFailure {

            }
            state = state.copy(isLoading = false)
    }
}
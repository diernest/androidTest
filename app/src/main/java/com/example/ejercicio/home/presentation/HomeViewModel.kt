package com.example.ejercicio.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejercicio.core.domain.repository.MovieRepository
import com.example.ejercicio.home.presentation.components.FilterType
import com.example.ejercicio.home.presentation.components.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            supervisorScope {
                val upcoming = launch { getUpcomingMovies() }
                val popular = launch { getPopularMovies() }
                val filtered = launch { getMoviesByFilter() }
                listOf(upcoming, popular, filtered).forEach {
                    it.join()
                }
                state = state.copy(isLoading = false)
            }

        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ChangeFilter -> {
                if (event.filterType != state.selectedFilter) {
                    state = state.copy(
                        selectedFilter = event.filterType
                    )
                    viewModelScope.launch {
                        getMoviesByFilter()
                    }
                }
            }
            is HomeEvent.OnMovieClick -> {}
        }
    }

    private suspend fun getPopularMovies() {
        repository.getPopularMovies().collect {
            state = state.copy(
                popularMovies = it
            )
        }
        state = state.copy(isLoading = false)
    }

    private suspend fun getUpcomingMovies() {
        /*repository.getUpcomingMovies().onSuccess {
            state = state.copy(
                upcomingMovies = it
            )
        }.onFailure {

        }
        state = state.copy(isLoading = false)*/
        repository.getUpcomingMovies().collect {
            state = state.copy(
                upcomingMovies = it
            )
        }
    }

    private suspend fun getMoviesByFilter() {
        val result = when (state.selectedFilter) {
            FilterType.SPANISH -> repository.getMoviesByLanguage(language = "es")
            FilterType.NINETY_THREE -> repository.getMoviesByYear(year = 1993)
        }

        result.collect() {
            if (it.isNotEmpty()) {
                state = state.copy(
                    filteredMovies = it.subList(0, 6) //TODO, export to Use Case
                )
            }
        }
    }
}
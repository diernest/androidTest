package com.example.ejercicio.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ejercicio.R
import com.example.ejercicio.home.presentation.components.*

const val COLUMNS_IN_GRID = 2

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { GridItemSpan(currentLineSpan = COLUMNS_IN_GRID) }) {
            HomeHeader()
        }
        if (state.upcomingMovies.isNotEmpty()) {
            item(span = { GridItemSpan(currentLineSpan = COLUMNS_IN_GRID) }) {
                HomeMovieList(title = stringResource(R.string.upcomming_releases),
                    posters = state.upcomingMovies.map { it.poster })
            }
        }
        if (state.popularMovies.isNotEmpty()) {
            item(span = { GridItemSpan(currentLineSpan = COLUMNS_IN_GRID) }) {
                HomeMovieList(title = stringResource(R.string.popular_movies),
                    posters = state.popularMovies.map { it.poster })
            }
        }
        if (state.filteredMovies.isNotEmpty()) {
            item(span = { GridItemSpan(currentLineSpan = COLUMNS_IN_GRID) }) {
                HomeRecommended(selectedFilter = state.selectedFilter, onFilterClick = {
                    viewModel.onEvent(HomeEvent.ChangeFilter(it))
                })
            }
        }
        items(state.filteredMovies) {
            HomeMoviePoster(imageUrl = it.poster, posterSize = HomeMoviePosterSize.BIG)
        }
    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
package com.example.ejercicio.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ejercicio.R
import com.example.ejercicio.home.presentation.components.HomeHeader
import com.example.ejercicio.home.presentation.components.HomeMovieList

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 25.dp)
    ) {
        item {
            HomeHeader()
        }

        if (state.upcomingMovies.isNotEmpty()) {
            item {
                HomeMovieList(
                    title = stringResource(R.string.upcomming_releases),
                    posters = state.upcomingMovies.map { it.poster })
            }
        }
        item{ Spacer(modifier = Modifier.height(26.dp))}
        if (state.popularMovies.isNotEmpty()) {
            item {
                HomeMovieList(
                    title = stringResource(R.string.popular_movies),
                    posters = state.popularMovies.map { it.poster })
            }
        }

    }
    if (state.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
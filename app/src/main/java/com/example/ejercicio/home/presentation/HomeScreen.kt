package com.example.ejercicio.home.presentation

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ejercicio.R
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
            Box(modifier = Modifier.fillMaxWidth().height(110.dp),
            contentAlignment = Alignment.Center){
                Image(
                    painter = painterResource(id = R.drawable.logo_marca),
                    contentDescription = "Logo de aplicación",
                    modifier = Modifier.padding(vertical = 30.dp)
                )
            }
        }

        if (state.upcoming.isNotEmpty()) {
            item {
                HomeMovieList(title = "Próximos Estrenos",
                    posters = state.upcoming.map { it.poster })
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
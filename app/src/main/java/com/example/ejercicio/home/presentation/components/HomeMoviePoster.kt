package com.example.ejercicio.home.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage


@Composable
fun HomeViewPoster(
    image: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    AsyncImage(model = image,
        modifier = modifier,
        contentDescription = "Portada de pelicula"
    )
}
package com.example.ejercicio.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.ejercicio.R
import com.example.ejercicio.core.domain.model.Movie

@Composable
fun HomeRecommended(
    selectedFilter: FilterType,
    onFilterClick: (FilterType) -> Unit,
    movieList: List<Movie>,
    modifier: Modifier = Modifier,
    onMovieClick: (Movie) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
            CategoryTitle(title = stringResource(R.string.recommended))
            FilterPillContainer(
                filters = FilterType.values().toList(),
                selectedFilter = selectedFilter, onFilterClick = onFilterClick
            )
    }
}
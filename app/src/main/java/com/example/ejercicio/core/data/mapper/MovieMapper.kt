package com.example.ejercicio.core.data.mapper

import com.example.ejercicio.core.data.remote.dto.MovieApi
import com.example.ejercicio.core.data.remote.dto.MovieResult
import com.example.ejercicio.core.domain.model.Movie

fun MovieResult.toDomain() : Movie {
    return Movie(
        description = this.overview,
        title = this.title,
        releaseYear = this.releaseDate.substring(0,4).toInt(),
        language = this.originalLanguage,
        rating = this.voteAverage,
        poster = MovieApi.BASE_IMAGE + this.posterPath,
        genres =  this.genreIds
    )
}
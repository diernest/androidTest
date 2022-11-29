package com.example.ejercicio.core.data.mapper

import com.example.ejercicio.core.data.local.entity.MovieEntity
import com.example.ejercicio.core.data.local.entity.MovieType
import com.example.ejercicio.core.data.remote.dto.MovieApi
import com.example.ejercicio.core.data.remote.dto.MovieResult
import com.example.ejercicio.core.domain.model.Movie

fun MovieResult.toDomain() : Movie {
    return Movie(
        id = this.id,
        poster = MovieApi.BASE_IMAGE + this.posterPath,
    )
}

fun Movie.toEntity(type: MovieType): MovieEntity{
    return MovieEntity(
        id = this.id,
        poster = this.poster,
        type = type
    )
}

fun MovieEntity.toDomain(): Movie{
    return Movie(
        id = this.id,
        poster = this.poster,
    )
}
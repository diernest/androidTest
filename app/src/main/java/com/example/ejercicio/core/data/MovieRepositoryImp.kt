package com.example.ejercicio.core.data.remote

import com.example.ejercicio.core.data.mapper.toDomain
import com.example.ejercicio.core.data.remote.dto.MovieApi
import com.example.ejercicio.core.domain.model.Movie
import com.example.ejercicio.core.domain.repository.MovieRepository

class MovieRepositoryImp(
    val api: MovieApi
) : MovieRepository {
    override suspend fun getUpcomingMovies(): Result<List<Movie>> {

        return try {
            val results = api.getUpcomingMovies().results
            Result.success(results.map { it.toDomain() })
        }catch (e: Exception){
            Result.failure(e)
        }
    }




}
package com.example.ejercicio.core.data.remote

import com.example.ejercicio.core.data.local.MovieDao
import com.example.ejercicio.core.data.local.entity.MovieType
import com.example.ejercicio.core.data.mapper.toDomain
import com.example.ejercicio.core.data.mapper.toEntity
import com.example.ejercicio.core.data.remote.dto.MovieApi
import com.example.ejercicio.core.data.remote.extensions.resultOf
import com.example.ejercicio.core.domain.model.Movie
import com.example.ejercicio.core.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImp(
    private val api: MovieApi,
    private val dao: MovieDao
) : MovieRepository {

    override fun getUpcomingMovies() : Flow<List<Movie>> {
        return flow {
            //TODO, Return only 1 MovieListItem with each type of movie?
            val localMovies = dao.getMovies().filter { it.type == MovieType.UPCOMING }
            emit(localMovies.map { it.toDomain() })
            getUpcomingMoviesRemotely().onSuccess {
                emit(it)
            }.onFailure {
            }
        }

    }

    private suspend fun getUpcomingMoviesRemotely() = resultOf {
        val results = api.getUpcomingMovies().results
        val movies = results.map { it.toDomain() }
        movies.forEach {
            dao.insertMovie(it.toEntity(MovieType.UPCOMING))
        }
        movies
    }

    override suspend fun getPopularMovies() = resultOf {
        val results = api.getPopularMovies().results
        results.map { it.toDomain() }
    }

    override suspend fun getMoviesByYear(year: Int) = resultOf {
        val results = api.getMoviesByYear(year).results
        results.map { it.toDomain() }
    }

    override suspend fun getMoviesByLanguage(language: String) = resultOf {
        val results = api.getMoviesByLanguage(language).results
        results.map { it.toDomain() }
    }

    //Version anterior antes de usar ResultExtensions
    /*
    override suspend fun getUpcomingMovies(): Result<List<Movie>> {
        return try {
            val results = api.getUpcomingMovies().results
            Result.success(results.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }*/

    /*override suspend fun getPopularMovies(): Result<List<Movie>> {
        return try {
            val results = api.getPopularMovies().results
            Result.success(results.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }*/


}
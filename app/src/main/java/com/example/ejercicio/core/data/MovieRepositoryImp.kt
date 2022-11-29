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

    override fun getPopularMovies() : Flow<List<Movie>> {
        return flow {
            //TODO, Return only 1 MovieListItem with each type of movie?
            val localMovies = dao.getMovies().filter { it.type == MovieType.TREDING }
            emit(localMovies.map { it.toDomain() })
            getPopularMoviesRemotely().onSuccess {
                emit(it)
            }.onFailure {
            }
        }
    }

    private suspend fun getPopularMoviesRemotely() = resultOf {
        val results = api.getPopularMovies().results
        val movies = results.map { it.toDomain() }
        movies.forEach {
            dao.insertMovie(it.toEntity(MovieType.TREDING))
        }
        movies
    }

    override fun getMoviesByYear(year: Int) : Flow<List<Movie>> {
        return flow {
            //TODO, Return only 1 MovieListItem with each type of movie?
            val localMovies = dao.getMovies().filter { it.type == MovieType.NINETY_THREE }
            emit(localMovies.map { it.toDomain() })
            getMoviesByYearRemotely(year).onSuccess {
                emit(it)
            }.onFailure {
            }
        }
    }

    private suspend fun getMoviesByYearRemotely(year: Int) = resultOf {
        val results = api.getMoviesByYear(year).results
        val movies = results.map { it.toDomain() }
        movies.forEach {
            dao.insertMovie(it.toEntity(MovieType.NINETY_THREE))
        }
        movies
    }

    override fun getMoviesByLanguage(language: String) : Flow<List<Movie>> {
        return flow {
            //TODO, Return only 1 MovieListItem with each type of movie?
            val localMovies = dao.getMovies().filter { it.type == MovieType.SPANISH }
            emit(localMovies.map { it.toDomain() })
            getMoviesByLanguageRemotely(language).onSuccess {
                emit(it)
            }.onFailure {
            }
        }
    }
    private suspend fun getMoviesByLanguageRemotely(language: String) = resultOf {
        val results = api.getMoviesByLanguage(language).results
        val movies = results.map { it.toDomain() }
        movies.forEach {
            dao.insertMovie(it.toEntity(MovieType.SPANISH))
        }
        movies
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
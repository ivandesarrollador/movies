package com.example.peliculas.repository

import com.example.peliculas.core.InternetCheck
import com.example.peliculas.data.local.AppDataBase
import com.example.peliculas.data.local.LocalMovieDataSource
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.model.toMovieEntity
import com.example.peliculas.data.remote.RemoteMovieDataSource

class MovieRepositoryImpl(
    private val dataSource: RemoteMovieDataSource,
    private val dataSourceLocal: LocalMovieDataSource
) : MovieRepository {

    override suspend fun getUpcomingMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getUpComingMovies().results.forEach { movie ->
                dataSourceLocal.saveModel(movie.toMovieEntity("upcoming"))
            }
            dataSource.getUpComingMovies()
        } else {
            dataSourceLocal.getUpComingMovies()
        }
        AppDataBase.get

    }

    override suspend fun getTopRatedMovies(): MovieList {

        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getTopRatedMovies().results.forEach { movie ->
                dataSourceLocal.saveModel(movie.toMovieEntity("toprated"))
            }
            dataSource.getTopRatedMovies()
        } else {
            dataSourceLocal.getTopRatedMovies()
        }
    }

    override suspend fun getPopularMovies(): MovieList {
        return if (InternetCheck.isNetworkAvailable()) {
            dataSource.getPopularMovies().results.forEach { movie ->
                dataSourceLocal.saveModel(movie.toMovieEntity("popular"))
            }
            dataSource.getPopularMovies()
        } else {
            dataSourceLocal.getPopularMovies()
        }

    }

}
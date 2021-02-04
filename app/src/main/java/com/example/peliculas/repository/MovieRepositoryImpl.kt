package com.example.peliculas.repository

import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.remote.MovieDataSource

class MovieRepositoryImpl(private val dataSource : MovieDataSource): MovieRepository {
    override suspend fun getUpcomingMovies(): MovieList  = dataSource.getUpComingMovies()

    override suspend fun getTopRatedMovies(): MovieList  = dataSource.getTopRatedMovies()

    override suspend fun getPopularMovies(): MovieList  = dataSource.getPopularMovies()

}
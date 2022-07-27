package com.example.peliculas.data.local

import com.example.peliculas.aplication.AppConstants
import com.example.peliculas.data.model.MovieEntity
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.data.model.toMovieList

class LocalMovieDataSource(private val movieDao: MovieDao) {

    suspend fun getUpComingMovies() : MovieList{
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }

    suspend fun getTopRatedMovies() : MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()

    }

    suspend fun getPopularMovies() : MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()

    }

    suspend fun saveModel(movieEntity: MovieEntity){
        movieDao.saveMovie(movieEntity)
    }
}
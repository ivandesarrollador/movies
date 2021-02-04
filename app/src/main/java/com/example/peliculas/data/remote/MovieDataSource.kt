package com.example.peliculas.data.remote

import com.example.peliculas.data.model.MovieList

class MovieDataSource {

    fun getUpComingMovies() : MovieList{
        return MovieList()
    }

    fun getTopRatedMovies() : MovieList{
        return MovieList()
    }

    fun getPopularMovies() : MovieList {
        return  MovieList()
    }
}
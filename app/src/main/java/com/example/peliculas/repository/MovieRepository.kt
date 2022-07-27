package com.example.peliculas.repository

import com.example.peliculas.data.model.Movie
import com.example.peliculas.data.model.MovieList

interface MovieRepository {
    //Una corutina es una funcion sin callbak la cual tiene la propiedad de ser cancelada

    suspend fun getUpcomingMovies(): List<Movie>
    suspend fun getTopRatedMovies() : MovieList
    suspend fun getPopularMovies() : MovieList

}
package com.example.peliculas.data.remote

import com.example.peliculas.aplication.AppConstants
import com.example.peliculas.data.model.MovieList
import com.example.peliculas.repository.WebService

class MovieDataSource (private val webservice : WebService){

    suspend fun getUpComingMovies() : MovieList = webservice.getUpcomingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies() : MovieList = webservice.getTopRatedMovies(AppConstants.API_KEY)

    suspend fun getPopularMovies() : MovieList =  webservice.getPopularMovies(AppConstants.API_KEY)

}
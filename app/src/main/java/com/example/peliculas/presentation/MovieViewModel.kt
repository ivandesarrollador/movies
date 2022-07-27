package com.example.peliculas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.example.peliculas.core.Resource
import com.example.peliculas.repository.MovieRepository
import kotlinx.coroutines.Dispatchers


class MovieViewModel(private val repo: MovieRepository): ViewModel(){

    fun fetchMainScreenMovies() = liveData(Dispatchers.IO){
        emit(Resource.Loading())
        try {
            emit(Resource.Success(repo.getUpcomingMovies()))
        }catch (e : Exception){
            emit(Resource.Failure(e))
        }
    }
}

class MoviewViewModelfactory(private val repo: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }
}
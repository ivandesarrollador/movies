package com.example.peliculas.ui.movie

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.peliculas.R
import com.example.peliculas.core.Resource
import com.example.peliculas.data.local.AppDataBase
import com.example.peliculas.data.local.LocalMovieDataSource
import com.example.peliculas.data.model.Movie
import com.example.peliculas.data.remote.RemoteMovieDataSource
import com.example.peliculas.databinding.FragmentMovieBinding
import com.example.peliculas.presentation.MovieViewModel
import com.example.peliculas.presentation.MoviewViewModelfactory
import com.example.peliculas.repository.MovieRepositoryImpl
import com.example.peliculas.repository.RetrofitClient
import com.example.peliculas.ui.movie.adapters.MovieAdapter
import com.example.peliculas.ui.movie.adapters.concat.PopularConcatAdapter
import com.example.peliculas.ui.movie.adapters.concat.TopRatedConcatAdapter
import com.example.peliculas.ui.movie.adapters.concat.UpcomingConcatAdapter


class MovieFragment : Fragment(R.layout.fragment_movie), MovieAdapter.OnMovieClickListener {

    private lateinit var binding: FragmentMovieBinding

    // Inyección de dependencias manual
    private val viewModel by viewModels<MovieViewModel> {
        MoviewViewModelfactory(
            MovieRepositoryImpl(

                RemoteMovieDataSource(RetrofitClient.webservice),
                LocalMovieDataSource(AppDataBase.getDataBase(requireContext()).movieDao())
            )
        )
    }

    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)
        binding.root
        concatAdapter = ConcatAdapter()
        getMovies()
    }


    private fun getMovies() {
        viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner) { restult ->
            when (restult) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE

                    Log.d(
                        "LIVEDATA",
                        " ${restult.data.first}   ${restult.data.second}  ${restult.data.third}"
                    )

                    concatAdapter.apply {
                        addAdapter(
                            0,
                            UpcomingConcatAdapter(
                                MovieAdapter(
                                    restult.data.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            1,
                            TopRatedConcatAdapter(
                                MovieAdapter(
                                    restult.data.second.results,
                                    this@MovieFragment
                                )
                            )
                        )
                        addAdapter(
                            2,
                            PopularConcatAdapter(
                                MovieAdapter(
                                    restult.data.third.results,
                                    this@MovieFragment
                                )
                            )
                        )
                    }

                    binding.rvMovies.adapter = concatAdapter
                }

                is Resource.Failure -> {
                    binding.progressBar.visibility = View.GONE

                    Log.d("ERROR", "Loading..")
                }
            }
        }
    }

    override fun onMovieClick(movie: Movie) {

        val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toDouble().toFloat(),
            movie.vote_count,
            movie.overview,
            movie.title,
            movie.original_language,
            movie.release_date
        )

        findNavController().navigate(action)
    }
}
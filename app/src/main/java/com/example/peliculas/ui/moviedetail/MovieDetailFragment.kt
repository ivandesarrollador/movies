package com.example.peliculas.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var mBinding : FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMovieDetailBinding.bind(view)
        mBinding.root
    }


}
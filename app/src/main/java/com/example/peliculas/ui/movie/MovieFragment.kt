package com.example.peliculas.ui.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieBinding


class MovieFragment : Fragment(R.layout.fragment_movie) {

    private lateinit var mBinding : FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMovieBinding.bind(view)
        mBinding.root
    }
}
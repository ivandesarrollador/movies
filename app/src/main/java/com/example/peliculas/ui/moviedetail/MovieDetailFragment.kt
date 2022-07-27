package com.example.peliculas.ui.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.peliculas.R
import com.example.peliculas.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var mBinding : FragmentMovieDetailBinding
    private val args by navArgs<MovieDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentMovieDetailBinding.bind(view)
        mBinding.root

        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.posterImageUrl}")
            .centerCrop().into(mBinding.shapeableImageView)
        Glide.with(requireContext()).load("https://image.tmdb.org/t/p/w500/${args.backgroundImageUrl}")
            .centerCrop().into(mBinding.imgBackground)

        mBinding.txtTitle.text = args.title
        mBinding.txtDescription.text = args.overView
        mBinding.txtCalendar.text = args.releaseDate
        mBinding.txtLanguage.text =  "Lenguaje ${args.lenguage}"
        mBinding.txtRating.text = "${args.voteAverage} ${args.voteCount} Reviews )"

    }


}
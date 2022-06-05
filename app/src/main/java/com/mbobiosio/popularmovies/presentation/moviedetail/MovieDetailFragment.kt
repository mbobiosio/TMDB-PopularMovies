package com.mbobiosio.popularmovies.presentation.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.mbobiosio.popularmovies.data.remote.model.movie.Movie
import com.mbobiosio.popularmovies.databinding.FragmentMovieDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding

    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var movieModel: Movie

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getArgs()
    }

    private fun getArgs() {
        movieModel = args.movie

        bindMovieDetails()
    }

    private fun bindMovieDetails() = with(binding) {
        binding.apply {
            movie = movieModel
        }
    }
}

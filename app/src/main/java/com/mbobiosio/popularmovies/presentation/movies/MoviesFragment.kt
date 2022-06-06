package com.mbobiosio.popularmovies.presentation.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.mbobiosio.popularmovies.data.remote.model.movie.Movie
import com.mbobiosio.popularmovies.databinding.FragmentMoviesBinding
import com.mbobiosio.popularmovies.util.safeNavigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviesBinding

    private val viewModel by viewModels<MoviesViewModel>()

    private val moviesAdapter by lazy {
        MoviesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()

        setupViews()
    }

    private fun observeViewModel() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                moviesAdapter.submitData(it)
            }
        }
    }

    private fun setupViews() = with(binding) {
        movies.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(activity, 2)
            adapter = moviesAdapter
        }

        moviesAdapter.itemClickListener = object : ItemClickListener {
            override fun onItemClick(movie: Movie?) {
                movie?.let {
                    findNavController().safeNavigate(
                        MoviesFragmentDirections.actionMoviesToDetails(it)
                    )
                }
            }
        }

        lifecycleScope.launch {
            moviesAdapter.loadStateFlow.collectLatest { loadState ->
                with(binding) {
                    progressBar.isVisible = loadState.refresh is LoadState.Loading
                    errorMessage.isVisible = loadState.refresh is LoadState.Error
                }
            }
        }
    }
}

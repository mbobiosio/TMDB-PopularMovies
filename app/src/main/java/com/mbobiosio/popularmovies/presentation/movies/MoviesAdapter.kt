package com.mbobiosio.popularmovies.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbobiosio.popularmovies.data.remote.model.movie.Movie
import com.mbobiosio.popularmovies.databinding.ItemMovieBinding

/**
 * @Author Mbuodile Obiosio
 * https://linktr.ee/mbobiosio
 */
class MoviesAdapter : PagingDataAdapter<Movie, MoviesAdapter.MoviesAdapterVH>(MovieComparator()) {
    lateinit var itemClickListener: ItemClickListener

    private class MovieComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesAdapterVH {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesAdapterVH(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapterVH, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    inner class MoviesAdapterVH(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieItem: Movie) {
            with(binding) {
                movie = movieItem
                clickListener = itemClickListener
                executePendingBindings()
            }
        }
    }
}

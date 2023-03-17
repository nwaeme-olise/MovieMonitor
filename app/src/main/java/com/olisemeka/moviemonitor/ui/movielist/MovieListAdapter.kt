package com.olisemeka.moviemonitor.ui.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.olisemeka.moviemonitor.data.MovieResult
import com.olisemeka.moviemonitor.R
import com.olisemeka.moviemonitor.util.Constants
import com.olisemeka.moviemonitor.util.GenreIdConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieListAdapter(private val context: Context): PagingDataAdapter<MovieResult, MovieListAdapter.MovieHolder>(MovieDiffCallback) {

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage: ImageView = itemView.findViewById(R.id.ivMovieImage)
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        val tvMovieReleaseDate: TextView = itemView.findViewById(R.id.tvMovieReleaseDate)
        val tvMovieGenre: TextView = itemView.findViewById(R.id.tvMovieGenre)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        var movie: MovieResult? = null

        init{
            itemView.setOnClickListener {view ->
                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movie!!)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.movie = movie
            holder.tvMovieTitle.text = movie.title ?: ""

            val formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
            val releaseDate = movie.releaseDate
            if (releaseDate != null) {
                holder.tvMovieReleaseDate.text = LocalDate.parse(releaseDate).format(formatter)
            }

            holder.ratingBar.rating = (movie.rating ?: 0) as Float
            val movieImageUrl = "${Constants.IMAGE_BASE_URL}${movie.imagePath}"
            Glide.with(context)
                .load(movieImageUrl)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .transform(RoundedCorners(20))
                .into(holder.ivMovieImage)

            holder.tvMovieGenre.text = GenreIdConverter.convertIdToGenre(movie.genreIds)
        }
    }

    object MovieDiffCallback: DiffUtil.ItemCallback<MovieResult>(){
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }

    }
}
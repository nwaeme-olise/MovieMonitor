package com.olisemeka.moviemonitor.ui.movielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.findNavController
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

class MovieListAdapter(private val context: Context): ListAdapter<MovieResult, MovieListAdapter.MovieHolder>(MovieDiffCallback) {

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage: ImageView = itemView.findViewById(R.id.ivMovieImage)
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        val tvMovieReleaseDate: TextView = itemView.findViewById(R.id.tvMovieReleaseDate)
        val tvMovieGenre: TextView = itemView.findViewById(R.id.tvMovieGenre)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
        var moviePosition = 0

        init{
            itemView.setOnClickListener {view ->
                val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(moviePosition)
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
        holder.moviePosition = position
        holder.tvMovieTitle.text = movie.title

        var formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy")
        holder.tvMovieReleaseDate.text = LocalDate.parse(movie.releaseDate).format(formatter)

        holder.ratingBar.rating = movie.rating
        val movieImageUrl = "${Constants.IMAGE_BASE_URL}${movie.imagePath}"
        Glide.with(context)
            .load(movieImageUrl)
            .centerCrop()
            .transform(RoundedCorners(20))
            .into(holder.ivMovieImage)

        holder.tvMovieGenre.text = GenreIdConverter.convertIdToGenre(movie.genreIds)

    }

    object MovieDiffCallback: DiffUtil.ItemCallback<MovieResult>(){
        override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
            return oldItem == newItem
        }

    }
}
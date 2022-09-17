package com.olisemeka.moviemonitor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieListAdapter(val movies:List<MovieResult>): RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivMovieImage: ImageView = itemView.findViewById(R.id.ivMovieImage)
        val tvMovieTitle: TextView = itemView.findViewById(R.id.tvMovieTitle)
        val tvMovieReleaseDate: TextView = itemView.findViewById(R.id.tvMovieReleaseDate)
        val tvMovieGenre: TextView = itemView.findViewById(R.id.tvMovieGenre)
        val ratingBar: RatingBar = itemView.findViewById(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movies[position]
        holder.tvMovieTitle.text = movie.title
        holder.tvMovieReleaseDate.text = movie.releaseDate
        holder.ratingBar.rating = movie.rating
        val movieImageUrl = "${Constants.IMAGE_BASE_URL}${movie.imagePath}"
    }

    override fun getItemCount() = movies.size
}
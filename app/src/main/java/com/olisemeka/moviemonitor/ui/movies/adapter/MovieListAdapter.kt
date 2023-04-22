package com.olisemeka.moviemonitor.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.olisemeka.moviemonitor.R
import com.olisemeka.moviemonitor.data.source.local.model.MovieResult

class MovieListAdapter(private val listener: (MovieResult) -> Unit)
    : PagingDataAdapter<MovieResult, MovieViewHolder>(MovieDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
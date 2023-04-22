package com.olisemeka.moviemonitor.ui.movies.adapter

import androidx.recyclerview.widget.DiffUtil
import com.olisemeka.moviemonitor.data.source.local.model.MovieResult

object MovieDiffCallback : DiffUtil.ItemCallback<MovieResult>() {

    override fun areItemsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieResult, newItem: MovieResult): Boolean {
        return oldItem == newItem
    }
}
package com.olisemeka.moviemonitor.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MovieListResult(
    val results: List<MovieResult>,

    @SerializedName("total_pages")
    val totalPages: Int
)

@Parcelize
@Entity(tableName="movie")
data class MovieResult(
    @SerializedName("poster_path")
    val imagePath: String?,

    val overview:String?,

    @SerializedName("release_date")
    val releaseDate: String?,

    @SerializedName("original_title")
    val title: String?,

    @SerializedName("genre_ids")
    val genreIds: List<Int>?,

    @SerializedName("popularity")
    val rating: Float?,
) : Parcelable{
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
}


package com.olisemeka.moviemonitor.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Constants {
    const val DB_NAME = "movie_db"
    const val BASE_URL = "https://api.themoviedb.org/4/"
    const val API_KEY = "28e5090dcd3cb625aa474f0234f1151b"
    const val SORT_ORDER = "release_date.desc"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"
    const val DATE_TIME_PATTERN = "dd-MMMM-yyyy"
    val RELEASE_DATE_LTE = LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString()
}
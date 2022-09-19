package com.olisemeka.moviemonitor

object GenreIdConverter {
    fun convertIdToGenre(genreIds: List<Int>): String{
        val convertedGenreIds: MutableList<String> = mutableListOf()
        for (genreId in genreIds){
            when (genreId){
                28 -> convertedGenreIds.add("Action")
                12 -> convertedGenreIds.add("Adventure")
                16 -> convertedGenreIds.add("Animation")
                35 -> convertedGenreIds.add("Comedy")
                99 -> convertedGenreIds.add("Documentary")
                18 -> convertedGenreIds.add("Drama")
                10751 -> convertedGenreIds.add("Family")
                14 -> convertedGenreIds.add("Fantasy")
                36 -> convertedGenreIds.add("History")
                27 -> convertedGenreIds.add("Horror")
                10402 -> convertedGenreIds.add("Music")
                9648 -> convertedGenreIds.add("Mystery")
                10749 -> convertedGenreIds.add("Romance")
                878 -> convertedGenreIds.add("Sci-Fi")
                10770 -> convertedGenreIds.add("TV Movie")
                53 -> convertedGenreIds.add("Thriller")
                10752 -> convertedGenreIds.add("Thriller")
                37 -> convertedGenreIds.add("Western")
                else -> convertedGenreIds.add("Unknown")
            }
        }
        return convertedGenreIds.joinToString(", ")
    }
}
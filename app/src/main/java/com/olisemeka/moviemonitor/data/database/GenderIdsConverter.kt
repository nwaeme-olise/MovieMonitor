package com.olisemeka.moviemonitor.data.database

import androidx.room.TypeConverter

class GenderIdsConverter {
    @TypeConverter
    fun fromGenderIds(genderIds: List<Int>?): String?{
        return genderIds?.joinToString(",")
    }

    @TypeConverter
    fun toGenderIds(genderIdsString: String?): List<Int>?{
        return genderIdsString?.split(",")?.map{it.toInt()}?.toList()
    }
}
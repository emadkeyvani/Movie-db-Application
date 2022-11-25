package com.keyvani.movieapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.keyvani.movieapplication.utils.Constants

@Entity(tableName = Constants.MOVIES_TABLE)
data class MovieEntity(
    @PrimaryKey
    var id: Int = 0,
    var poster: String = "",
    var title: String = "",
    var rate: String = "",
    var contry: String = "",
    var year: String = ""
)

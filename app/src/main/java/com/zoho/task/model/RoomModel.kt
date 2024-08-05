package com.zoho.task.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "room")
data class RoomModel(
    val title: String,
    val releaseDate: String,
    val status: String,
    val tagline: String,
    val runtime: Int,
    val originalLanguage: String,
    val posterPath: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)

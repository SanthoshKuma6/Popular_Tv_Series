package com.zoho.task.network

import androidx.room.Insert
import androidx.room.Query
import com.zoho.task.model.RoomModel

interface RoomInterface {
    @Query("SELECT * FROM room ")
    fun getMovie(): List<RoomModel>
    @Insert
    fun insertMovie(movie: RoomModel)
}
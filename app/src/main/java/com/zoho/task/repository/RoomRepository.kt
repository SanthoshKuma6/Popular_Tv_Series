package com.zoho.task.repository

import com.zoho.task.dao.RoomDao
import com.zoho.task.model.RoomModel
import kotlinx.coroutines.delay

class RoomRepository(private val roomDao : RoomDao) {

     fun insert(saveData: RoomModel) = roomDao.roomInterface().insertMovie(saveData)

    fun getList() = roomDao.roomInterface().getMovie()

}

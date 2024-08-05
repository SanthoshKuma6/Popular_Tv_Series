package com.zoho.task.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoho.task.model.RoomModel
import com.zoho.task.repository.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel (
    private val roomRepository : RoomRepository) : ViewModel() {

    fun insert(saveData: RoomModel) = viewModelScope.launch {
        roomRepository.insert(saveData)
    }
    fun getList() = roomRepository.getList()

}
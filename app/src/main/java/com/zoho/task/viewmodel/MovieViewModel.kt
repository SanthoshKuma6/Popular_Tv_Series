package com.zoho.task.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoho.task.model.Movie
import com.zoho.task.network.Response
import com.zoho.task.repository.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val addEmployeeList = MutableStateFlow<Response<Movie>>(Response.Loading(false))
    val movieState: StateFlow<Response<Movie>> = addEmployeeList
    fun getMovieList(token: String) {

        viewModelScope.launch {
            addEmployeeList.value = Response.Loading(true)
            try {
                val data = movieRepository.getMovieList(token)
                if (data.isSuccessful) {
                    addEmployeeList.value = Response.Loading(false)
                    addEmployeeList.value = Response.Success(data.body())
                } else {
                    addEmployeeList.value = Response.Loading(false)
                    addEmployeeList.value = Response.Error(data.message())
                }
            } catch (e: Exception) {
                Log.d("TAG", "login: ${e.message}")
            }
        }
    }
}




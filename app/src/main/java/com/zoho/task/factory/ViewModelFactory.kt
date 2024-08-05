package com.zoho.task.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.zoho.task.repository.MovieRepository
import com.zoho.task.viewmodel.MovieViewModel

class ViewModelFactory(private val movieRepository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
            return MovieViewModel(movieRepository) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
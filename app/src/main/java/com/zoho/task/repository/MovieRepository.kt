package com.zoho.task.repository

import com.zoho.task.model.Movie
import com.zoho.task.network.ApiService

class MovieRepository(private val apiService: ApiService) {

    suspend fun getMovieList(token:String):retrofit2.Response<Movie> {
        return apiService.getMovies(token)

    }
}
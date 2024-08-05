package com.zoho.task.network

import com.zoho.task.model.Movie
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/550?/")
    suspend fun getMovies(
        @Query("api_key") apiKey: String,
    ): Response<Movie>

    object NetworkClient {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        val apiService: ApiService by lazy {
            retrofit.create(ApiService::class.java)
        }
    }

}
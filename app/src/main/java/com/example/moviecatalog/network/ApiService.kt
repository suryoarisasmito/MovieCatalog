package com.example.moviecatalog.network

import com.example.moviecatalog.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    companion object {
        const val API_KEY = "1da5859b301508ddd2bd07da35c9f3c5"
    }

    @GET("now_playing?api_key=$API_KEY&language=en-US&page=1")
    fun getListMovie() : Call<MovieResponse>
}
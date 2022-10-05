package com.example.moviecatalog.network

import com.example.moviecatalog.DetailResponse
import com.example.moviecatalog.MovieResponse
import com.example.moviecatalog.model.GenresItem
import com.example.moviecatalog.model.GenresResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    companion object {
        const val API_KEY = "1da5859b301508ddd2bd07da35c9f3c5"
    }

    @GET("movie/now_playing?api_key=$API_KEY&language=en-US&page=1")
    fun getListMovie() : Call<MovieResponse>

    @GET("movie/{id}?api_key=$API_KEY&language=en-US")
    fun getDetailMovie(
        @Path("id") id : String
    ): Call<DetailResponse>

    @GET("genre/movie/list?api_key=$API_KEY&language=en-US")
    fun getGenresMovie() : Call<GenresResponse>

    @GET("movie/popular?api_key=$API_KEY&language=en-US&page=1")
    fun getPopularMovie() : Call<MovieResponse>
}
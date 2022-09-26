package com.example.moviecatalog.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.moviecatalog.DetailResponse
import com.example.moviecatalog.GenresItem
import com.example.moviecatalog.databinding.ActivityDetailBinding
import com.example.moviecatalog.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    companion object{
        const val MOVIE_ID = "id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getStringExtra(MOVIE_ID)
        if (movieId != null) {
            getDetailMovie(movieId)
        }

    }

    private fun setDetailMovie(detailMovie: DetailResponse) {
        val list: ArrayList<GenresItem> = arrayListOf()
        Glide.with(this@DetailActivity)
            .load(detailMovie.backdropPath)
            .into(binding.imgPosterDetail)

        binding.tvGenre.text = list[0].name
        binding.tvRating.text = detailMovie.voteAverage.toString()
        binding.tvTitleDetail.text = detailMovie.title
        binding.tvDescription.text = detailMovie.overview

    }

    private fun getDetailMovie(movieId: String) {
        val client = ApiConfig.getApiService().getDetailMovie(movieId)
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                if (response.isSuccessful){
                    val movieResponseDetailItem = response.body()
                    if (movieResponseDetailItem != null ) {
                        setDetailMovie(movieResponseDetailItem)
                    }
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("DetailActivity", "onFailure: ${t.message}")
            }

        })
    }


}
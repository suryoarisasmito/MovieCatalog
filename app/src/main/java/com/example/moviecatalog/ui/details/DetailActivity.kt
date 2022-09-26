package com.example.moviecatalog.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.example.moviecatalog.DetailResponse
import com.example.moviecatalog.GenresItem
import com.example.moviecatalog.databinding.ActivityDetailBinding
import com.example.moviecatalog.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding
    private var list = ArrayList<GenresItem>()

    companion object{
        const val MOVIE_ID = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getSerializableExtra(MOVIE_ID.toString())
        getDetailMovie(movieId)

    }

    private fun setDetailMovie(detailMovie: DetailResponse) {
        val list = ArrayList<GenresItem>()
        Glide.with(this@DetailActivity)
            .load("https://image.tmdb.org/t/p/original${detailMovie.backdropPath}")
            .into(binding.imgPosterDetail)

        binding.tvRating.text = detailMovie.voteAverage.toString()
        binding.tvStatus.text = detailMovie.status
        binding.tvTitleDetail.text = detailMovie.title
        binding.tvDescription.text = detailMovie.overview

    }

    private fun getDetailMovie(movieId: Serializable?) {
        showLoading(true)
        val client = ApiConfig.getApiService().getDetailMovie(movieId.toString())
        client.enqueue(object : Callback<DetailResponse> {
            override fun onResponse(
                call: Call<DetailResponse>,
                response: Response<DetailResponse>
            ) {
                showLoading(false)
                val movieResponseDetail = response.body()
                if (movieResponseDetail != null){
                    setDetailMovie(movieResponseDetail)
                }
            }

            override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                Log.d("DetailActivity", "onFailure: ${t.message}")
                showLoading(false)
            }

        })
    }

    private fun showLoading(isLoading : Boolean) {
        if (isLoading){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

}
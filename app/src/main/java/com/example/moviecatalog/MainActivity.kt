package com.example.moviecatalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.databinding.ActivityMainBinding
import com.example.moviecatalog.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val layoutManager = LinearLayoutManager(this)
        binding.rvListMovie.layoutManager = layoutManager

        getMovies()
    }

    private fun getMovies() {
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getListMovie()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                binding.progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val dataList = response.body()?.results
                    Log.d("TEST_DEBUG", "onResponse: $dataList")
                    showMovieList(dataList)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                Log.e("MainActivity", "onFailure: ${t.message}")
            }

        })
    }

    private fun showMovieList(movieList: List<ResultsItem>?) {
        movieList?.let { list ->
            movieAdapter = MovieAdapter(list)
            binding.rvListMovie.apply {
                adapter = movieAdapter
            }
        }
    }
}
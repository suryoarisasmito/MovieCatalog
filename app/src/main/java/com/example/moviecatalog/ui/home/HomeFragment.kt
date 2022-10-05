package com.example.moviecatalog.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.MovieResponse
import com.example.moviecatalog.ResultsItem
import com.example.moviecatalog.databinding.FragmentHomeBinding
import com.example.moviecatalog.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var movieAdapter: MovieAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieList()
    }

    private fun getMovieList() {
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
                layoutManager = LinearLayoutManager(requireContext())
                adapter = movieAdapter
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }


}
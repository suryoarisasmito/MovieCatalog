package com.example.moviecatalog.ui.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.MovieResponse
import com.example.moviecatalog.R
import com.example.moviecatalog.ResultsItem
import com.example.moviecatalog.databinding.FragmentHomeBinding
import com.example.moviecatalog.databinding.FragmentPopularBinding
import com.example.moviecatalog.network.ApiConfig
import com.example.moviecatalog.ui.home.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularFragment : Fragment() {

    private val binding by lazy { FragmentPopularBinding.inflate(layoutInflater) }
    private lateinit var popularAdapter: PopularAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getPopularMovie()
    }

    private fun getPopularMovie() {
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getPopularMovie()
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                binding.progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val dataList = response.body()?.results
                    Log.d("TEST_DEBUG", "onResponse: $dataList")
                    showPopularMovie(dataList)
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                Log.e("PopularFragment", "onFailure: ${t.message}")
            }

        })
    }

    private fun showPopularMovie(movieList: List<ResultsItem>?) {
        movieList?.let { list ->
            popularAdapter = PopularAdapter(list)
            binding.rvPopular.apply {
                layoutManager = GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
                adapter = popularAdapter
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
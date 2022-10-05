package com.example.moviecatalog.ui.genres

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviecatalog.databinding.FragmentGenresBinding
import com.example.moviecatalog.model.GenresItem
import com.example.moviecatalog.model.GenresResponse
import com.example.moviecatalog.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class GenresFragment : Fragment() {

    private val binding by lazy { FragmentGenresBinding.inflate(layoutInflater) }
    private lateinit var genresAdapter: GenresAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getListGenres()
    }

    private fun getListGenres() {
        binding.progressBar.visibility = View.VISIBLE
        val client = ApiConfig.getApiService().getGenresMovie()
        client.enqueue(object: Callback<GenresResponse> {
            override fun onResponse(call: Call<GenresResponse>, response: Response<GenresResponse>) {
                binding.progressBar.visibility = View.INVISIBLE
                if (response.isSuccessful) {
                    val dataList = response.body()?.genres
                    Log.d("TEST_DEBUG", "onResponse: $dataList")
                    showGenresList(dataList)
                }
            }

            override fun onFailure(call: Call<GenresResponse>, t: Throwable) {
                binding.progressBar.visibility = View.INVISIBLE
                Log.e("MainActivity", "onFailure: ${t.message}")
            }

        })
    }

    private fun showGenresList(genresList: List<GenresItem?>?) {
        genresList.let{ listGenres ->
            genresAdapter = GenresAdapter(listGenres)
            binding.rvListGenres.apply {
                layoutManager = GridLayoutManager(context, 3)
                adapter = genresAdapter
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


package com.example.moviecatalog.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.ResultsItem

class MovieAdapter(private val listMovie : List<ResultsItem>) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle : TextView = view.findViewById(R.id.tv_title)
        val tvDesc : TextView = view.findViewById(R.id.tv_description)
        val tvRelease : TextView = view.findViewById(R.id.tv_release_date)
        val tvAverage : TextView = view.findViewById(R.id.tv_vote_average)

        val imgPoster : ImageView = view.findViewById(R.id.img_poster)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_movie, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovie[position]
        holder.tvTitle.text = movie.title
        holder.tvDesc.text = movie.overview
        holder.tvRelease.text = movie.releaseDate
        holder.tvAverage.text = movie.voteAverage.toString()

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/original${listMovie[position].backdropPath}")
            .into(holder.imgPoster)
    }

    override fun getItemCount(): Int = listMovie.size

}
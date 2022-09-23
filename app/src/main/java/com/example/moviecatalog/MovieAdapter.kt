package com.example.moviecatalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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
        holder.tvTitle.text = listMovie.get(position).title
        holder.tvDesc.text = listMovie.get(position).overview
        holder.tvRelease.text = listMovie?.get(position)!!.releaseDate
        holder.tvAverage.text = listMovie?.get(position)!!.voteAverage.toString()

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/original" + listMovie[position]?.backdropPath)
            .into(holder.imgPoster)
    }

    override fun getItemCount(): Int = listMovie?.size!!

}
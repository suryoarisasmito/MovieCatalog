package com.example.moviecatalog.ui.genres

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.moviecatalog.R
import com.example.moviecatalog.model.GenresItem
import com.example.moviecatalog.ui.details.DetailActivity
import com.example.moviecatalog.ui.details_genres.DetailGenresActivity
import com.example.moviecatalog.ui.home.MovieAdapter

class GenresAdapter(private val listGenres: List<GenresItem?>?) : RecyclerView.Adapter<GenresAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        GenresAdapter.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genresList = listGenres?.get(position)
        if (genresList != null) {
            holder.tvListGenres.text = genresList.name
        }

        val context = holder.itemView.context

        holder.tvListGenres.setOnClickListener {
            val moveListGenres = Intent(context, DetailGenresActivity::class.java)
            moveListGenres.putExtra(DetailActivity.MOVIE_ID.toString(), listGenres?.get(position)?.id)
            context.startActivity(moveListGenres)
        }
    }

    override fun getItemCount(): Int = listGenres?.size!!


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvListGenres: TextView = view.findViewById(R.id.tv_listGenres)
    }

}
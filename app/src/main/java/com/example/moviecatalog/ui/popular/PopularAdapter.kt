package com.example.moviecatalog.ui.popular

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviecatalog.R
import com.example.moviecatalog.ResultsItem
import com.example.moviecatalog.ui.details.DetailActivity

class PopularAdapter(private val listPopular : List<ResultsItem>) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_popular, viewGroup, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listPopular[position]
        holder.tvTitlePopular.text = movie.title
//        holder.tvDesc.text = movie.overview
//        holder.tvRelease.text = movie.releaseDate
//        holder.tvAverage.text = movie.voteAverage.toString()

        Glide.with(holder.itemView)
            .load("https://image.tmdb.org/t/p/original${listPopular[position].backdropPath}")
            .into(holder.imgPopular)

        val context = holder.itemView.context

        holder.itemPopular.setOnClickListener {
            val moveDetail = Intent(context, DetailActivity::class.java)
            moveDetail.putExtra(DetailActivity.MOVIE_ID.toString(), listPopular[position].id)
            context.startActivity(moveDetail)
        }
    }

    override fun getItemCount(): Int = listPopular.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitlePopular : TextView = view.findViewById(R.id.tv_title_popular)
//        val tvDesc : TextView = view.findViewById(R.id.tv_description)
//        val tvRelease : TextView = view.findViewById(R.id.tv_release_date)
//        val tvAverage : TextView = view.findViewById(R.id.tv_vote_average)

        val itemPopular : RelativeLayout = view.findViewById(R.id.item_popular)
        val imgPopular : ImageView = view.findViewById(R.id.img_popular)
    }


}
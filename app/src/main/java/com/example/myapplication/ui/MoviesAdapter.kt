package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.datasource.network.Constants
import com.example.myapplication.data.models.Movies
import com.example.myapplication.data.models.Results
import com.example.myapplication.databinding.ItemMovieBinding

class MoviesAdapter : RecyclerView.Adapter<MovieHolder>() {
    private lateinit var  items: List<Results>

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(p0.context))
        ItemMovieBinding.bind(view.root)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(p0: MovieHolder, p1: Int) {
        p0.bind(items[p1])
    }

    override fun getItemCount(): Int = if(::items.isInitialized) { items.count() } else { 0 }

    fun setData(movies : List<Results>) {
        items = movies
        notifyDataSetChanged()
    }

}

class MovieHolder(private val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Results) {
        Glide.with(binding.root.context)
                .load(Constants.BASE_IMAGE_URL + item.posterPath)
                .centerCrop()
                .into(binding.imageView)
    }

}

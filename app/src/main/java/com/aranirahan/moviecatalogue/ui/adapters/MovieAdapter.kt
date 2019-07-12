package com.aranirahan.moviecatalogue.ui.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_movie.view.*


class MovieAdapter(private val movies: List<Movie>,
                   private val clickListener: (Int) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.content_movie, parent, false))
    }

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie) {
            val v = itemView
            Picasso.get().load(movie.image).into(v.iv_movie)
            v.tv_title.text = movie.title
            v.tv_desc.text = movie.description
            v.setOnClickListener { clickListener(movie.id) }
        }
    }
}
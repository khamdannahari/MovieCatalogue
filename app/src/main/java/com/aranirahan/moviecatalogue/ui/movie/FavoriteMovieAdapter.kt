package com.aranirahan.moviecatalogue.ui.movie

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.ui.detailmovie.DetailMovieActivity
import com.squareup.picasso.Picasso


class FavoriteMovieAdapter :
    PagedListAdapter<Movie, FavoriteMovieAdapter.BookmarkViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.tvTitle.text = movie.title
            holder.tvDescription.text = movie.description
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, DetailMovieActivity::class.java)
                val courseId = movie.id
                intent.putExtra(DetailMovieActivity.ID_MOVIE, courseId)
                context.startActivity(intent)
            }

            Picasso.get().load(movie.image).into(holder.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.content_movie, parent, false)
        return BookmarkViewHolder(view)
    }

/*    internal fun getItemById(swipedPosition: Int): Movie? {
        return getItem(swipedPosition)
    }*/

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_desc)
        val imgPoster: ImageView = itemView.findViewById(R.id.iv_movie)

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }
}
package com.aranirahan.TvShowcatalogue.ui.tvshow

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
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity
import com.squareup.picasso.Picasso


class FavoriteTvShowAdapter
    : PagedListAdapter<TvShow, FavoriteTvShowAdapter.BookmarkViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: BookmarkViewHolder, position: Int) {
        val bookmark = getItem(position)
        if (bookmark != null) {
            holder.tvTitle.text = bookmark.title
            holder.tvDescription.text = bookmark.description
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, DetailTvShowActivity::class.java)
                val courseId = bookmark.id
                intent.putExtra(DetailTvShowActivity.ID_TV_SHOW, courseId)
                context.startActivity(intent)
            }

            Picasso.get().load(bookmark.image).into(holder.imgPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.content_tv_show, parent, false)
        return BookmarkViewHolder(view)
    }

/*    internal fun getItemById(swipedPosition: Int): TvShow? {
        return getItem(swipedPosition)
    }*/

    inner class BookmarkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_desc)
        val imgPoster: ImageView = itemView.findViewById(R.id.iv_movie)

    }

    companion object {

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }
}
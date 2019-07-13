package com.aranirahan.moviecatalogue.ui.tvshow


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.content_tv_show.view.*


class TvShowAdapter(private val clickListener: (Int) -> Unit)
    : RecyclerView.Adapter<TvShowAdapter.ViewHolder>() {

    private var tvShow: List<TvShow> = emptyList()

    fun submitList(tvShow: List<TvShow>) {
        this.tvShow = tvShow
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.content_tv_show, parent, false))
    }

    override fun getItemCount() = tvShow.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tvShow[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(tvShow: TvShow) {
            val v = itemView
            Picasso.get().load(tvShow.image).into(v.iv_movie)
            v.tv_title.text = tvShow.title
            v.tv_desc.text = tvShow.description
            v.setOnClickListener { clickListener(tvShow.id) }
        }
    }
}
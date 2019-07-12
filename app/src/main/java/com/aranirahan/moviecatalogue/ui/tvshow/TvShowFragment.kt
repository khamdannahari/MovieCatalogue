package com.aranirahan.moviecatalogue.ui.tvshow


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.model.TvShow
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity.Companion.ID_TV_SHOW
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.startActivity


class TvShowFragment : Fragment() {

    private var data = listOf<TvShow>()
    private val vmMain by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = vmMain.tvShows

        rv_tv_show.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(data) { idMovie ->
                context?.startActivity<DetailTvShowActivity>(ID_TV_SHOW to idMovie)
            }
        }
    }
}

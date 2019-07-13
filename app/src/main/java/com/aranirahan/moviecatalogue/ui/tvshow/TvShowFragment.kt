package com.aranirahan.moviecatalogue.ui.tvshow


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aranirahan.moviecatalogue.R
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity
import com.aranirahan.moviecatalogue.ui.detailtvshow.DetailTvShowActivity.Companion.ID_TV_SHOW
import com.aranirahan.moviecatalogue.ui.main.MainViewModel
import com.aranirahan.moviecatalogue.viewmodel.ViewModelFactory
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.jetbrains.anko.startActivity


class TvShowFragment : Fragment() {

    private var data = listOf<TvShow>()
    private val vmMain by lazy { activity?.let { obtainViewModel(it) } }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        data = vmMain?.tvShows ?: listOf()
        Log.d("dataTvShowAll", Gson().toJson(data))
        Log.d("dataTvShow1", Gson().toJson(data[0]))
        Log.d("dataTvShow2", Gson().toJson(data[1]))
        Log.d("dataTvShow3", Gson().toJson(data[2]))
        Log.d("dataTvShow4", Gson().toJson(data[3]))
        Log.d("dataTvShow5", Gson().toJson(data[4]))
        Log.d("dataTvShow6", Gson().toJson(data[5]))
        Log.d("dataTvShow7", Gson().toJson(data[6]))
        Log.d("dataTvShow8", Gson().toJson(data[7]))
        Log.d("dataTvShow9", Gson().toJson(data[8]))
        Log.d("dataTvShow10", Gson().toJson(data[9]))
        Log.d("dataTvShow11", Gson().toJson(data[10]))
        Log.d("dataTvShow12", Gson().toJson(data[11]))
        Log.d("dataTvShow13", Gson().toJson(data[12]))
        Log.d("dataTvShow14", Gson().toJson(data[13]))
        Log.d("dataTvShow15", Gson().toJson(data[14]))
        Log.d("dataTvShow16", Gson().toJson(data[15]))

        rv_tv_show.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = TvShowAdapter(data) { idMovie ->
                context?.startActivity<DetailTvShowActivity>(ID_TV_SHOW to idMovie)
            }
        }
    }

    private fun obtainViewModel(activity: FragmentActivity): MainViewModel {
        // Use a Factory to inject dependencies into the ViewModel
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProviders.of(activity, factory).get(MainViewModel::class.java)
    }
}

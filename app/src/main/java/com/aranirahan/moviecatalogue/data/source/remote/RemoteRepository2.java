package com.aranirahan.moviecatalogue.data.source.remote;

import android.os.Handler;

import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse;
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.aranirahan.moviecatalogue.utils.EspressoIdlingResource2;

import java.util.List;


public class RemoteRepository2 {

    private static RemoteRepository2 INSTANCE;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;
    private JsonHelper jsonHelper;
    private GetTvShowCallback getTvShowCallback;

    private RemoteRepository2(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository2 getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository2(helper);
        }
        return INSTANCE;
    }

    public void getMovieResponseList(GetMoviesCallback callback) {
        EspressoIdlingResource2.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onMoviesReceived(jsonHelper.loadMovieResponseList());
            EspressoIdlingResource2.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getMovieResponse(int idMovie, GetMovieCallback callback) {
        EspressoIdlingResource2.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onMovieReceived(jsonHelper.loadMovieResponse(idMovie));
            EspressoIdlingResource2.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }


    public void getTvShowResponseList(GetTvShowsCallback callback) {
        EspressoIdlingResource2.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onTvShowsReceived(jsonHelper.loadTvShowResponseList());
            EspressoIdlingResource2.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getTvShowResponse(int idMovie, GetTvShowCallback callback) {
        EspressoIdlingResource2.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onTvShowReceived(jsonHelper.loadTvShowResponse(idMovie));
            EspressoIdlingResource2.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }


    public interface GetMoviesCallback {
        void onMoviesReceived(List<MovieResponse> movieResponseList);

        void onDataNotAvailable();
    }

    public interface GetMovieCallback {
        void onMovieReceived(MovieResponse movieResponse);

        void onDataNotAvailable();
    }

    public interface GetTvShowsCallback {
        void onTvShowsReceived(List<TvShowResponse> tvShowResponseList);

        void onDataNotAvailable();
    }

    public interface GetTvShowCallback {
        void onTvShowReceived(TvShowResponse tvShowResponse);

        void onDataNotAvailable();
    }


    public void setGetTvShowCallback(GetTvShowCallback getTvShowCallback)
    {
        this.getTvShowCallback=getTvShowCallback;
    }

}


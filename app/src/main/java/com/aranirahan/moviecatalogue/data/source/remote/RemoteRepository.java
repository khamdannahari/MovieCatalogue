package com.aranirahan.moviecatalogue.data.source.remote;

import android.os.Handler;

import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse;
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.aranirahan.moviecatalogue.utils.EspressoIdlingResource;
import com.aranirahan.moviecatalogue.utils.JsonHelper;

import java.util.List;


public class RemoteRepository {

    private static RemoteRepository INSTANCE;
    private JsonHelper jsonHelper;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper helper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(helper);
        }
        return INSTANCE;
    }


    public void getMovieResponseList(GetMoviesCallback callback) {
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onMoviesReceived(jsonHelper.loadMovieResponseList());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getMovieResponse(int idMovie, GetMovieCallback callback) {
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onMovieReceived(jsonHelper.loadMovieResponse(idMovie));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }


    public void getTvShowResponseList(GetTvShowsCallback callback) {
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onTvShowsReceived(jsonHelper.loadTvShowResponseList());
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY_IN_MILLIS);
    }

    public void getTvShowResponse(int idMovie, GetTvShowCallback callback) {
        EspressoIdlingResource.increment();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            callback.onTvShowReceived(jsonHelper.loadTvShowResponse(idMovie));
            EspressoIdlingResource.decrement();
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

//   Di syarat submission nya g perlu di rubah ya kak?
/*    public LiveData<ApiResponse<List<MovieResponse>>> getMovieResponseList() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<MovieResponse>>> resultCourse = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.Companion.success(jsonHelper.loadMovieResponseList
            ()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultCourse;
    }

    public LiveData<ApiResponse<MovieResponse>> getMovieResponse(int idMovie) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<MovieResponse>> resultContent = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultContent.setValue(ApiResponse.Companion.success(jsonHelper.loadMovieResponse
            (idMovie)));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultContent;
    }

    public LiveData<ApiResponse<List<TvShowResponse>>> getTvShowResponseList() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TvShowResponse>>> resultCourse = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultCourse.setValue(ApiResponse.Companion.success(jsonHelper.loadTvShowResponseList
            ()));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultCourse;
    }

    public LiveData<ApiResponse<TvShowResponse>> getTvShowResponse(int idTvShow) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<TvShowResponse>> resultContent = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            resultContent.setValue(ApiResponse.Companion.success(jsonHelper.loadTvShowResponse
            (idTvShow)));
            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultContent;
    }*/

}


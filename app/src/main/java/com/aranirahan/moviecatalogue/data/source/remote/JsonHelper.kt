package com.aranirahan.moviecatalogue.data.source.remote


import android.app.Application
import com.aranirahan.moviecatalogue.data.source.remote.response.MovieResponse
import com.aranirahan.moviecatalogue.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*

class JsonHelper(private val application: Application) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = application.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }

    }

    fun loadMovieResponseList(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("movie.json"))
            val listArray = responseObject.getJSONArray("movie")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val date = movie.getString("date")
                val image = movie.getInt("image")

                val courseResponse = MovieResponse(
                    id = id,
                    title = title,
                    description = description,
                    date = date,
                    image = image
                )
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadMovieResponse(idMovie: Int): MovieResponse {
        val fileName = String.format("movie_%s.json", idMovie)
        var movieResponse = MovieResponse()
        try {

            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)

                val id = responseObject.getInt("id")
                val title = responseObject.getString("title")
                val description = responseObject.getString("description")
                val date = responseObject.getString("date")
                val image = responseObject.getInt("image")

                movieResponse = MovieResponse(
                    id = id,
                    title = title,
                    description = description,
                    date = date,
                    image = image
                )
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return movieResponse
    }

    fun loadTvShowResponseList(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()

        try {
            val responseObject = JSONObject(parsingFileToString("tvShow.json"))
            val listArray = responseObject.getJSONArray("tvShow")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getInt("id")
                val title = movie.getString("title")
                val description = movie.getString("description")
                val date = movie.getString("date")
                val image = movie.getInt("image")

                val courseResponse = TvShowResponse(
                    id = id,
                    title = title,
                    description = description,
                    date = date,
                    image = image
                )
                list.add(courseResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShowResponse(idTvShow: Int): TvShowResponse {
        val fileName = String.format("tvShow_%s.json", idTvShow)
        var movieResponse = TvShowResponse()
        try {

            val result = parsingFileToString(fileName)
            if (result != null) {
                val responseObject = JSONObject(result)

                val id = responseObject.getInt("id")
                val title = responseObject.getString("title")
                val description = responseObject.getString("description")
                val date = responseObject.getString("date")
                val image = responseObject.getInt("image")

                movieResponse = TvShowResponse(
                    id = id,
                    title = title,
                    description = description,
                    date = date,
                    image = image
                )
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return movieResponse
    }
}

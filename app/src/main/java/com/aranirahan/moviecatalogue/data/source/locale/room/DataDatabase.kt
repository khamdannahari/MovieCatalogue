package com.aranirahan.moviecatalogue.data.source.locale.room

import com.aranirahan.moviecatalogue.data.source.locale.entity.Movie
import com.aranirahan.moviecatalogue.data.source.locale.entity.TvShow

import android.content.Context

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
abstract class DataDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {

        private var INSTANCE: DataDatabase? = null

        private val sLock = Any()

        fun getInstance(context: Context): DataDatabase {
            synchronized(sLock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataDatabase::class.java, "MovieAndTvShow.db"
                    )
                        .build()
                }
                return INSTANCE as DataDatabase
            }
        }
    }

}

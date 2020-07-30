package com.example.newsfeed.data.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsfeed.data.db.dao.ArticleDao
import com.example.newsfeed.data.db.entities.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {

    init {
        Log.e("##AppDB", "created")
    }

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile
        private var instance: AppDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDataBase(context)
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, AppDB::class.java, "newsFeed.db").build()
    }
}
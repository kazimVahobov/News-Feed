package com.example.newsfeed.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsfeed.db.dao.ArticleDao
import com.example.newsfeed.db.entities.Article

@Database(
    entities = [Article::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {

        @Volatile
        private var instanse: AppDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instanse ?: synchronized(LOCK) {
            instanse ?: buildDataBase(context)
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context, AppDB::class.java, "newsFeed.db").build()
    }
}
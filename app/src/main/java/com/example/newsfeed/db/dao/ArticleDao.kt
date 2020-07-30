package com.example.newsfeed.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsfeed.db.entities.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveArticles(articles: MutableList<Article>)

    @Query("SELECT * FROM Article")
    fun getQuotes(): MutableList<Article>
}
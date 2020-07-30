package com.example.newsfeed.data.repositories

import android.util.Log
import com.example.newsfeed.data.db.AppDB
import com.example.newsfeed.data.db.entities.Article
import com.example.newsfeed.data.network.API
import com.example.newsfeed.data.network.SafeApiRequest
import com.example.newsfeed.data.network.responses.GetArticlesResponse
import com.example.newsfeed.utils.Constants
import com.example.newsfeed.utils.Coroutines

class ArticleRepository(private val api: API, private val appDB: AppDB) : SafeApiRequest() {

    init {
        Log.e("##ArticleRepository", "created")
    }

    private val apiKey = Constants.API_KEY

    suspend fun getArticles(
        qParam: String,
        fromDate: String,
        sortType: String,
        page: Int
    ): GetArticlesResponse {
        return apiRequest { api.getArticles(qParam, fromDate, sortType, apiKey, page) }
    }

    fun getArticles() = appDB.getArticleDao().getArticles()

    fun saveArticles(list: MutableList<Article>) {
        Coroutines.io {
            appDB.getArticleDao().saveArticles(list)
        }
    }
}
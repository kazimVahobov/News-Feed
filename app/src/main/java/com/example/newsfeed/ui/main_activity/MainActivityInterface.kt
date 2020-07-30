package com.example.newsfeed.ui.main_activity

import com.example.newsfeed.data.db.entities.Article

interface MainActivityInterface {
    interface View {
        fun onGetArticles(articles: MutableList<Article>)
    }

    interface Presenter {
        fun loadArticles()
    }
}
package com.example.newsfeed.ui.main_activity

import com.example.newsfeed.App
import com.example.newsfeed.data.network.NetworkConnectionInterceptor
import com.example.newsfeed.data.repositories.ArticleRepository
import com.example.newsfeed.utils.Coroutines
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainActivityPresenter(
    private val view: MainActivityInterface.View
) : MainActivityInterface.Presenter, KodeinAware {

    override val kodein by lazy { (App.instance as App).kodein }
    private val networkConnectionInterceptor by instance<NetworkConnectionInterceptor>()
    private val repository by instance<ArticleRepository>()

    override fun loadArticles() {
        val qParam = "android"
        val fromDate = "2019-04-00"
        val sortType = "publishedAt"
        val page = 2

        if (networkConnectionInterceptor.isInternetAvailable()) {
            Coroutines.main {
                val response = repository.getArticles(qParam, fromDate, sortType, page)
                response.articles?.let {
                    view.onGetArticles(response.articles!!)
                }
            }
        } else {

        }
    }
}
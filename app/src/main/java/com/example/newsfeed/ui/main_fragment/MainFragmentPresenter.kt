package com.example.newsfeed.ui.main_fragment

import com.example.newsfeed.App
import com.example.newsfeed.data.network.NetworkConnectionInterceptor
import com.example.newsfeed.data.repositories.ArticleRepository
import com.example.newsfeed.utils.Coroutines
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class MainFragmentPresenter(private val view: MainFragmentInterface.View) :
    MainFragmentInterface.Presenter, KodeinAware {

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
                    repository.saveArticles(response.articles!!)
                }
            }
        } else {
            Coroutines.main {
                view.onGetArticles(repository.getArticles())
            }
        }
    }
}
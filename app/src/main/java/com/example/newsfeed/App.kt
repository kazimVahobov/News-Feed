package com.example.newsfeed

import android.app.Application
import com.example.newsfeed.data.db.AppDB
import com.example.newsfeed.data.network.API
import com.example.newsfeed.data.network.NetworkConnectionInterceptor
import com.example.newsfeed.data.repositories.ArticleRepository
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { API(instance()) }
        bind() from singleton { AppDB(instance()) }
        bind() from singleton { ArticleRepository(instance(), instance()) }
    }
}
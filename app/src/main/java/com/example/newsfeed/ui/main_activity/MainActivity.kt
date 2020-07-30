package com.example.newsfeed.ui.main_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsfeed.R
import com.example.newsfeed.data.event_bus.EBOnArticleClick
import com.example.newsfeed.data.network.NetworkConnectionInterceptor
import com.example.newsfeed.ui.main_fragment.MainFragment
import com.example.newsfeed.ui.web_view_fragment.WebViewFragment
import com.example.newsfeed.utils.snackBar
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

class MainActivity : AppCompatActivity(), MainActivityInterface.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.frame, MainFragment(), "MainFragment").commit()
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe
    public fun onArticleClicked(data: EBOnArticleClick) {
        if (!NetworkConnectionInterceptor.isInternetAvailable(this)) {
            rootLayout.snackBar(getString(R.string.no_internet))
            return
        }
        if (data.url.isNullOrEmpty()) {
            rootLayout.snackBar(getString(R.string.wrong_url))
            return
        }
        supportFragmentManager.beginTransaction()
            .add(R.id.frame, WebViewFragment.newInstance(data.url), "MainFragment").addToBackStack(null).commit()
    }

}
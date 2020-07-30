package com.example.newsfeed.ui.main_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsfeed.R
import com.example.newsfeed.data.db.entities.Article
import com.example.newsfeed.data.event_bus.EBOnArticleClick
import com.example.newsfeed.ui.adapters.MainListAdapter
import kotlinx.android.synthetic.main.fragment_main.*
import org.greenrobot.eventbus.EventBus

class MainFragment : Fragment(), MainFragmentInterface.View {
    lateinit var adapter: MainListAdapter
    lateinit var presenter: MainFragmentPresenter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MainFragmentPresenter(this)
        presenter.loadArticles()
    }

    override fun onGetArticles(articles: MutableList<Article>) {
        adapter = MainListAdapter(context!!, articles) { url -> onArticleClicked(url) }
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        recyclerView.adapter = adapter
    }

    private fun onArticleClicked(url: String) {
        EventBus.getDefault().post(EBOnArticleClick(url))
    }

    override fun hideLoading() {
        animationView.visibility = View.GONE
    }

    override fun showLoading() {
        animationView.visibility = View.VISIBLE
    }
}
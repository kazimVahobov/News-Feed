package com.example.newsfeed.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsfeed.R
import com.example.newsfeed.data.db.entities.Article
import com.example.newsfeed.utils.Utils

class MainListAdapter(
    private val context: Context,
    private val list: MutableList<Article>
) : RecyclerView.Adapter<MainListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val article = list[position]
        holder.relativeLayout.animation = AnimationUtils.loadAnimation(context, R.anim.fade_scale)
        holder.title.text = article.title
        holder.content.text = article.content
//        holder.time.text = Utils.formattingDate(article.publishedAt!!)
        Glide.with(context).load(article.urlToImage).into(holder.image)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.titleTV)
        var content: TextView = itemView.findViewById(R.id.contentTV)
        var time: TextView = itemView.findViewById(R.id.dateTV)
        var image: ImageView = itemView.findViewById(R.id.imageIV)
        var relativeLayout: LinearLayout = itemView.findViewById(R.id.linearLayout)
    }
}
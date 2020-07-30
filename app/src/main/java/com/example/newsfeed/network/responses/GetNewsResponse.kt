package com.example.newsfeed.network.responses

import com.example.newsfeed.db.entities.Article

data class GetNewsResponse(var status: String, var totalResults: Int?, var articles: MutableList<Article>?, var code: String?, var message: String?)
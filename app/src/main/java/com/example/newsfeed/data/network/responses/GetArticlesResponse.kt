package com.example.newsfeed.data.network.responses

import com.example.newsfeed.data.db.entities.Article

data class GetArticlesResponse(
    var status: String,
    var totalResults: Int?,
    var articles: MutableList<Article>?,
    var code: String?,
    var message: String?
)
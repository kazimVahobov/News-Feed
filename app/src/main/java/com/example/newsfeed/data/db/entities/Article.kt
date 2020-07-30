package com.example.newsfeed.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsfeed.utils.ItemType

@Entity
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var author: String?,
    var title: String?,
    var description: String?,
    var url: String?,
    var urlToImage: String?,
    var publishedAt: String?,
    var content: String?
)

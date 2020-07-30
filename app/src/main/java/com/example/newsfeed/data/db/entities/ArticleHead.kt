package com.example.newsfeed.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticleHead(
    @PrimaryKey(autoGenerate = false)
    var id: String?,
    var name: String?
)
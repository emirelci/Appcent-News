package com.ee.appcentnews.model

import com.google.gson.annotations.SerializedName

data class Articles(
        @SerializedName("status")
        val status: String,

        @SerializedName("totalResults")
        val totalResults: Int,

        @SerializedName("articles")
        var articles: List<News>
)

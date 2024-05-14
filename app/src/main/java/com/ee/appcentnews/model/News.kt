package com.ee.appcentnews.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "FavoriteArticlesTable",indices = [Index(value = ["title", "content"], unique = true)])
data class News(
    @ColumnInfo("author")
    @SerializedName("author")
    val author: String?,

    @ColumnInfo("title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo("description")
    @SerializedName("description")
    val description: String?,

    @ColumnInfo("url")
    @SerializedName("url")
    val url: String?,

    @ColumnInfo("urlToImage")
    @SerializedName("urlToImage")
    val urlToImage: String?,

    @ColumnInfo("publishedAt")
    @SerializedName("publishedAt")
    val publishedAt: String?,

    @ColumnInfo("content")
    @SerializedName("content")
    val content: String?
) : Parcelable {
    @PrimaryKey(true)
    var uuid: Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(author)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeString(url)
        parcel.writeString(urlToImage)
        parcel.writeString(publishedAt)
        parcel.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<News> {
        override fun createFromParcel(parcel: Parcel): News {
            return News(parcel)
        }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}
package com.example.foodwasteproject.engine.objects

import android.media.Image
import androidx.compose.runtime.Composable
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import coil.compose.AsyncImagePainter
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Article (
    @PrimaryKey val id: Int,
    @ColumnInfo("Title") val title: String,
    @ColumnInfo("Subtitle") val subtitle: String,
    @ColumnInfo("Publish_Date") val publishDate: String,
    @ColumnInfo("Thumbnail_URL") val thumbnailURL: String,
    @ColumnInfo("Banner_Image_URL") val bannerImageURL: String,
    @ColumnInfo("content") val content: String
)

@Dao
interface ArticleDao{
    @Query("SELECT * FROM article")
     fun getAll(): List<Article>

    @Insert
    fun insert(vararg article: Article)

    @Query("DElETE FROM article")
    fun clearArticles()

    @Query("SELECT id FROM article")
    fun getAllIDs() :List<Int>

}
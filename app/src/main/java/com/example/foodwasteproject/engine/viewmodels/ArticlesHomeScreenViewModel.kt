package com.example.foodwasteproject.engine.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import coil.compose.AsyncImage
import com.example.foodwasteproject.engine.database.LocalDatabase
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.engine.objects.ArticleDao
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getKoin

class ArticlesHomeScreenViewModel(
    val articleDao: ArticleDao
) : ViewModel() {
    init {

    }
    val allArticles = getAllArticles()

    @JvmName("taskToGetAllArticles")
    private fun getAllArticles(): List<Article>{
        val result = MutableLiveData<List<Article>>()
        viewModelScope.launch {
            val fetchedresult = articleDao.getAll()
            if(fetchedresult.isEmpty()){
                setUpArticles()
                val secondFetch = articleDao.getAll()
                result.postValue(secondFetch)
            }else {
                result.postValue(fetchedresult)
            }
        }
        return result.value ?: listOf(
            Article(
                id = 15,
                title = "One",
                subtitle = "Fail",
                publishDate = "10/10/2020",
                thumbnailURL = "https://picsum.photos/200",
                bannerImageURL = "https://picsum.photos/200",
                content = "TEST"
            ),
            Article(
                id = 16,
                title = "Test",
                subtitle = "Two",
                publishDate = "10/10/2020",
                thumbnailURL = "https://picsum.photos/200",
                bannerImageURL = "https://picsum.photos/200",
                content = "TEST"
            ),
            Article(
                id = 17,
                title = "Test",
                subtitle = "Three",
                publishDate = "10/10/2020",
                thumbnailURL = "https://picsum.photos/200",
                bannerImageURL = "https://picsum.photos/200",
                content = "TEST"
            ),
            Article(
                id = 18,
                title = "Test",
                subtitle = "Four",
                publishDate = "10/10/2020",
                thumbnailURL = "https://picsum.photos/200",
                bannerImageURL = "https://picsum.photos/200",
                content = "TEST"
            )
        )
    }

    fun setUpArticles() {
        val testArticle = Article(
            id = 1,
            title = "TestTitle",
            subtitle = "TestSub",
            publishDate = "10/10/2020",
            thumbnailURL = "https://picsum.photos/200",
            bannerImageURL = "https://picsum.photos/200",
            content = "TEST"
        )
        viewModelScope.launch{articleDao.insert(testArticle)}
    }
}
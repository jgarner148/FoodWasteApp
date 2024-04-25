package com.example.foodwasteproject.engine.viewmodels

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.lifecycle.ViewModel
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.engine.objects.ArticleDao
import kotlin.random.Random

class ArticlesHomeScreenViewModel(
    val articleDao: ArticleDao
) : ViewModel() {

    val allArticles = getAllArticles()

    @JvmName("taskToGetAllArticles")
    private fun getAllArticles(): List<Article>{
        val fetchedresult = articleDao.getAll()
        return if(fetchedresult.isEmpty()){
            setUpArticles()
            val secondFetch = articleDao.getAll()
            secondFetch
        }else {
            fetchedresult
        }
        }

    private fun setUpArticles() {
        repeat(15) {
            articleDao.insert(
                Article(
                    id = createID(),
                    title = LoremIpsum(Random.nextInt(2, 6)).values.first(),
                    subtitle = LoremIpsum(Random.nextInt(2, 10)).values.first(),
                    publishDate = randomDate(),
                    thumbnailURL = "https://picsum.photos/200",
                    bannerImageURL = "https://picsum.photos/1920/1080",
                    content = LoremIpsum(350).values.first()
                )
            )
        }
    }

    private fun randomDate() : String{
        val day = Random.nextInt(1,27).toString()
        val month = Random.nextInt(1,12).toString()
        val year = Random.nextInt(2020,2024).toString()
        return "$day/$month/$year"
    }

    private fun createID() : Long{
        val allIDs = articleDao.getAllIDs()
        var unique = false
        var id = Random.nextInt(1000, 9999)
        while (!unique){
            if(allIDs.contains(id)){
                id = Random.nextInt(1000, 9999)
            }else{
                unique = true
            }
        }
        return id.toLong()
    }

}
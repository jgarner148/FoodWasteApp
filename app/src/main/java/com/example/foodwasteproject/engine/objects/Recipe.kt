package com.example.foodwasteproject.engine.objects

data class Recipe (
    val title: String,
    val ingredients: List<String>,
    val description: String
)

fun List<Recipe>.toSubtitle(): String =
    if(this.isEmpty()){
        "No Recipes Yet"
    }else{
        this.joinToString { it.title }
    }

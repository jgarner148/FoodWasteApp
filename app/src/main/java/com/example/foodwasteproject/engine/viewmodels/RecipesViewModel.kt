package com.example.foodwasteproject.engine.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodwasteproject.engine.objects.IngredientsDao
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.objects.RecipeDao
import kotlinx.coroutines.launch

class RecipesViewModel(
    val recipesDao: RecipeDao,
    val ingredientsDao: IngredientsDao
) : ViewModel() {
    val allRecipes = recipesDao.getAll()
    val allIngredients = ingredientsDao.getAll()

    fun addRecipeToDatabase(recipe:Recipe){
        viewModelScope.launch {
            recipesDao.insert(recipe)
        }
    }
}
package com.example.foodwasteproject.engine.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodwasteproject.engine.objects.Ingredient
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
    val allIDs = ingredientsDao.getAllIDs()

    fun addRecipeToDatabase(recipe:Recipe){
        viewModelScope.launch {
            recipesDao.insert(recipe)
        }
    }

    fun deleteRecipe(recipe: Recipe){
        viewModelScope.launch{
            recipesDao.deleteRecipe(recipe)
        }
    }

    fun createMultipleIngredients(ingredients: List<String>){
        ingredients.forEach {
            ingredientsDao.insert(
                Ingredient(
                id = createIngredientID(),
                name = it
                )
            )
        }
    }

    private fun createIngredientID() : String{
        val allIDs = ingredientsDao.getAllIDs()
        var unique = false
        var id = kotlin.random.Random.nextInt(100000, 999999).toString()
        while (!unique){
            if(allIDs.contains(id)){
                id = kotlin.random.Random.nextInt(100000, 999999).toString()
            }else{
                unique = true
            }
        }
        return id
    }

}
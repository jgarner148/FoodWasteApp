package com.example.foodwasteproject.engine.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodwasteproject.engine.objects.Cupboard
import com.example.foodwasteproject.engine.objects.CupboardDao
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.objects.IngredientsDao
import java.lang.Exception

class CupboardScreenViewModel(
    private val cupboardDao: CupboardDao,
    val ingredientsDao: IngredientsDao
) : ViewModel() {
    val allCupboardIngredients = getIngredients()
    val allUsersIngredients = getUserIngredients()

    private fun getIngredients() : MutableList<Ingredient>{
        val cupboard = cupboardDao.getAll()
        return try {
            cupboard.ingredients.toMutableList()
        }catch (e: Exception){
            createCupboard()
            emptyList<Ingredient>().toMutableList()
        }
    }

    private fun getUserIngredients() : List<Ingredient>{
        return try {
            ingredientsDao.getAll()
        }catch (e: Exception){
            emptyList()
        }
    }

    fun deleteIngredient(ingredient: Ingredient){
        allCupboardIngredients.remove(ingredient)
        cupboardDao.AddIngredient(allCupboardIngredients)
    }

    fun createIngredient(name: String){
        val newIngredient = Ingredient(
            id = createIngredientID(),
            name = name
        )
        ingredientsDao.insert(newIngredient)
    }

    fun addIngredient(ingredient: Ingredient){
        allCupboardIngredients.add(ingredient)
        cupboardDao.AddIngredient(allCupboardIngredients)
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

    private fun createCupboard(){
        cupboardDao.insert(Cupboard(123456789, emptyList()))
    }

}
package com.example.foodwasteproject.engine.objects

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Recipe (
    @PrimaryKey val id: String,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("ingredients") val ingredients: List<Ingredient>,
    @ColumnInfo("description") val description: String
)
@Dao
interface RecipeDao{
    @Query("SELECT * FROM recipe")
    fun getAll(): List<Recipe>

    @Insert
    fun insert(vararg recipe: Recipe)

    @Query("SELECT id FROM recipe")
    fun getAllIDs() :List<String>

    @Delete
    fun deleteRecipe(recipe: Recipe)
}

fun List<Recipe>.toSubtitle(): String =
    if(this.isEmpty()){
        "No Recipes Yet"
    }else{
        this.joinToString { it.title }
    }
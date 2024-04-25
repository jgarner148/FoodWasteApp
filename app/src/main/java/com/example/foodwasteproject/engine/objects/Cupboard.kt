package com.example.foodwasteproject.engine.objects

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity
data class Cupboard (
    @PrimaryKey val id: Long,
    @ColumnInfo("ingredients") val ingredients: List<Ingredient>,
)

@Dao
interface CupboardDao{
    @Query("SELECT * FROM cupboard")
    fun getAll(): Cupboard

    @Insert
    fun insert(vararg cupboard: Cupboard)

    @Query("UPDATE cupboard SET ingredients = :newIngredients")
    fun AddIngredient(newIngredients: List<Ingredient>)

}
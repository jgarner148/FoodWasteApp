package com.example.foodwasteproject.engine.objects

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Ingredient (
    @PrimaryKey val id: String,
    @ColumnInfo("name") val name: String
)

@Dao
interface IngredientsDao{
    @Query("SELECT * FROM ingredient")
    fun getAll(): List<Ingredient>

    @Insert
    fun insert(vararg ingredient: Ingredient)

    @Query("SELECT id FROM ingredient")
    fun getAllIDs() :List<String>
}
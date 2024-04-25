package com.example.foodwasteproject.engine.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.engine.objects.ArticleDao
import com.example.foodwasteproject.engine.objects.Calendar
import com.example.foodwasteproject.engine.objects.CalendarDao
import com.example.foodwasteproject.engine.objects.CalendarDay
import com.example.foodwasteproject.engine.objects.CalendarDayDao
import com.example.foodwasteproject.engine.objects.Cupboard
import com.example.foodwasteproject.engine.objects.CupboardDao
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.objects.IngredientsDao
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.objects.RecipeDao

@Database(entities = [Article::class, Calendar::class, CalendarDay::class, Ingredient::class, Recipe::class, Cupboard::class], version = 3)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun articleDao() : ArticleDao

    abstract fun calendarDao() : CalendarDao

    abstract fun calendarDayDao() : CalendarDayDao

    abstract fun ingredientsDao(): IngredientsDao

    abstract fun recipeDao(): RecipeDao

    abstract fun cupboardDao(): CupboardDao
}
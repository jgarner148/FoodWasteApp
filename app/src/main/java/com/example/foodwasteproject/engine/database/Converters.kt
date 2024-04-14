package com.example.foodwasteproject.engine.database

import androidx.room.TypeConverter
import com.example.foodwasteproject.engine.objects.CalendarDay
import com.example.foodwasteproject.engine.objects.Recipe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters{
    @TypeConverter
    fun recipeToJson(value: List<Recipe>) = Json.encodeToString(value)

    @TypeConverter
    fun recipeToList(value: String) = Json.decodeFromString<List<Recipe>>(value)

    @TypeConverter
    fun calendarDayToJson(value: List<CalendarDay>) = Json.encodeToString(value)

    @TypeConverter
    fun calendarDayToList(value: String) = Json.decodeFromString<List<CalendarDay>>(value)
}
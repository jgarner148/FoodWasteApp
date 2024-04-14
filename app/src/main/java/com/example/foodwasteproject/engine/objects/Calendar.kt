package com.example.foodwasteproject.engine.objects

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Entity
data class Calendar (
    @PrimaryKey val id: Int,
    @ColumnInfo("Start_Date") val startDate: String,
    @ColumnInfo("End_Date") val endDate: String,
    @ColumnInfo("Days") val days: List<CalendarDay>
)

@Dao
interface CalendarDao{
    @Query("SELECT * FROM calendar")
    suspend fun getAll(): Calendar

    @Insert
    suspend fun insert(vararg calendar: Calendar)

}

@Entity
data class CalendarDay(
    @PrimaryKey val id: Int,
    @ColumnInfo("Name")val name: String,
    @ColumnInfo("Recipes")val recipes: List<Recipe>
)

@Dao
interface CalendarDayDao{
    @Query("SELECT * FROM calendarDay")
    suspend fun getAll(): CalendarDay

    @Insert
    suspend fun insert(vararg calendar: Calendar)

}


package com.example.foodwasteproject.engine.objects

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Entity
data class Calendar (
    @PrimaryKey val id: Long,
    @ColumnInfo("Start_Date") val startDate: String,
    @ColumnInfo("End_Date") val endDate: String,
    @ColumnInfo("Days") val days: List<CalendarDay>
)

@Dao
interface CalendarDao{
    @Query("SELECT * FROM calendar")
    fun getAll(): Calendar

    @Insert
    fun insert(vararg calendar: Calendar)

    @Query("DElETE FROM calendar")
    fun clearCalendar()

    @Query("UPDATE calendar SET Days = :newDays")
    fun AddRecipe(newDays: List<CalendarDay>)

    @Query("SELECT id FROM calendar")
    fun getAllIDs() :List<Int>

}

@Serializable
@Entity
data class CalendarDay(
    @PrimaryKey val id: Long,
    @ColumnInfo("Name")val name: String,
    @ColumnInfo("Recipes")val recipes: List<Recipe>
)

@Dao
interface CalendarDayDao{
    @Query("SELECT * FROM calendarDay")
    fun getAll(): CalendarDay

    @Insert
    fun insert(vararg calendar: Calendar)

}



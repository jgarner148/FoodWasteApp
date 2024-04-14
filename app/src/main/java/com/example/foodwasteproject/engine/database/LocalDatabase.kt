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

@Database(entities = [Article::class, Calendar::class, CalendarDay::class], version = 2)
@TypeConverters(Converters::class)
abstract class LocalDatabase : RoomDatabase(){
    abstract fun articleDao() : ArticleDao

    abstract fun calendarDao() : CalendarDao

    abstract fun acalendarDayDao() : CalendarDayDao
}
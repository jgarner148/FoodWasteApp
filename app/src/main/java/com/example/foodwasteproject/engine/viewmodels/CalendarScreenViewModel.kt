package com.example.foodwasteproject.engine.viewmodels

import androidx.lifecycle.ViewModel
import com.example.foodwasteproject.engine.objects.Calendar
import com.example.foodwasteproject.engine.objects.CalendarDao
import com.example.foodwasteproject.engine.objects.CalendarDay
import com.example.foodwasteproject.engine.objects.CupboardDao
import com.example.foodwasteproject.engine.objects.IngredientsDao
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.objects.RecipeDao
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Random
import java.util.Calendar as JavaUtilCalendar

class CalendarScreenViewModel(
    val calendarDao: CalendarDao,
    val recipesDao: RecipeDao,
) : ViewModel() {

    val wholeCalendar = getCalendar()
    val allRecipes = getAllRecipes()
@JvmName("getWholeCalendarMethod")
    private fun getCalendar(): Calendar = try {
            val result = calendarDao.getAll()
            val sdf = SimpleDateFormat("dd - MMM")
            val endDate: Date = sdf.parse(result.endDate)
            if (System.currentTimeMillis() > endDate.time) {
                createNewCalendar()
                calendarDao.getAll()
            } else {
                result
            }
        } catch (e: Exception) {
        createNewCalendar()
        calendarDao.getAll()
    }
    @JvmName("getAllRecipesMethod")
    private fun getAllRecipes(): List<Recipe>{
        return recipesDao.getAll()
    }

    @JvmName("createCalendarMethod")
    private fun createNewCalendar(){
        val c: JavaUtilCalendar = JavaUtilCalendar.getInstance()
        c.set(JavaUtilCalendar.DAY_OF_WEEK, JavaUtilCalendar.MONDAY)
        val df: DateFormat = SimpleDateFormat("dd - MMM")
        val monday = df.format(c.time)
        c.add(JavaUtilCalendar.DAY_OF_WEEK, 6)
        val sunday = df.format(c.time)
        calendarDao.insert(
            Calendar(createCalendarID(), monday, sunday, listOf(
                CalendarDay(createDayID(1),"Monday", emptyList()),
                CalendarDay(createDayID(2),"Tuesday", emptyList()),
                CalendarDay(createDayID(3),"Wednesday", emptyList()),
                CalendarDay(createDayID(4),"Thursday", emptyList()),
                CalendarDay(createDayID(5),"Friday", emptyList()),
                CalendarDay(createDayID(6),"Saturday", emptyList()),
                CalendarDay(createDayID(7),"Sunday", emptyList()),
            ))
        )
    }
    @JvmName("addRecipeMethod")
    fun addRecipe(recipe: Recipe, id: Long){
        var newDays = emptyList<CalendarDay>()
        wholeCalendar.days.forEach {
            newDays = if(it.id == id){
                newDays.plus(CalendarDay(it.id,it.name, it.recipes.plus(recipe)))
            }else{
                newDays.plus(it)
            }
        }
        calendarDao.AddRecipe(newDays)
    }

    private fun createCalendarID() : Long{
        val formatter = DateTimeFormatter.ofPattern("yyMMdd")
        val date = LocalDateTime.now().format(formatter)

        val allIDs = calendarDao.getAllIDs()
        var unique = false
        var id = kotlin.random.Random.nextInt(10, 99)
        while (!unique){
            if(allIDs.contains(id)){
                id = kotlin.random.Random.nextInt(10, 99)
            }else{
                unique = true
            }
        }

        return "$date$id".toLong()
    }

    private fun createDayID(day:Int) : Long{
        val formatter = DateTimeFormatter.ofPattern("yyMMdd")
        val date = LocalDateTime.now().format(formatter)

        val allIDs = calendarDao.getAllIDs()
        var unique = false
        var id = kotlin.random.Random.nextInt(100, 999)
        while (!unique){
            if(allIDs.contains(id)){
                id = kotlin.random.Random.nextInt(100, 999)
            }else{
                unique = true
            }
        }

        val finalID = day.toString() + date.toString() + id.toString()
        return finalID.toLong()
    }

    fun generateRecipeSuggestion() :Recipe{
        return try {

            allRecipes.random()
        }catch (e: Exception){
            Recipe(
                "",
                "",
                emptyList(),
                ""
            )
        }
    }
}
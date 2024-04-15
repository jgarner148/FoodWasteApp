package com.example.foodwasteproject.engine.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodwasteproject.engine.objects.Calendar
import com.example.foodwasteproject.engine.objects.CalendarDao
import com.example.foodwasteproject.engine.objects.CalendarDay
import com.example.foodwasteproject.engine.objects.Recipe
import kotlinx.coroutines.launch

class CalendarScreenViewModel(
    val calendarDao: CalendarDao,
) : ViewModel() {

    val wholeCalendar = getCalendar()

    private fun getCalendar(): Calendar {
        val result = MutableLiveData<Calendar>()
        viewModelScope.launch {
            val fetchedresult = calendarDao.getAll()
            //Insert filling method here
            result.postValue(fetchedresult)
        }
        return result.value ?: Calendar(1, "F10th March", "F17th March", listOf(
            CalendarDay(1,"Monday", listOf(
                Recipe("Spag Bol", emptyList(), "Stuff"),
                Recipe("Mac and Cheese", emptyList(), "Stuff")
            )),
            CalendarDay(2,"Tuesday", emptyList()),
            CalendarDay(2,"Wednesday", emptyList()),
            CalendarDay(2,"Thursday", emptyList()),
            CalendarDay(2,"Friday", emptyList()),
            CalendarDay(2,"Saturday", emptyList()),
            CalendarDay(2,"Sunday", emptyList()),
        ))
    }
}
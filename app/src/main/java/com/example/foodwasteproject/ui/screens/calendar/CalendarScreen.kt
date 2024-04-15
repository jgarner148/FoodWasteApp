package com.example.foodwasteproject.ui.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodwasteproject.engine.objects.Calendar
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.objects.toSubtitle
import com.example.foodwasteproject.engine.viewmodels.CalendarScreenViewModel
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftTextRightDate
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
fun CalendarScreen() {
    val viewModel = getViewModel<CalendarScreenViewModel>()
    CalendarScreenContent(wholeCalendar = viewModel.wholeCalendar)
}
@Composable
fun CalendarScreenContent(
    wholeCalendar: Calendar
){
    Column {
        Row {
            TileBarLeftTextRightDate(title = "Calendar", subtitle = null, startDate = wholeCalendar.startDate, endDate = wholeCalendar.endDate)
        }
        LazyColumn {
            items(wholeCalendar.days){
                Row(modifier = Modifier.padding(10.dp)) {
                    Column {
                        Text(text = it.name, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Text(text = it.recipes.toSubtitle(), fontStyle = FontStyle.Italic)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    StandardButton(text = "Add", isActive = true, onClick = {//This will set the show bottom sheet to true
                     })
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
    }
}


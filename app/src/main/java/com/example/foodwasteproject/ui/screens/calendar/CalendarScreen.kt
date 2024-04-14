package com.example.foodwasteproject.ui.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftTextRightDate
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
fun CalendarScreen() {
    val daysofweek = arrayOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")
    Column {
        Row {
            TileBarLeftTextRightDate(title = "Calendar", subtitle = null, startDate = "TEST", endDate = "TEST")
        }
        LazyColumn {
            items(daysofweek){
                Row(modifier = Modifier.padding(10.dp)) {
                    Column {
                        Text(text = it)
                        Text(text = "Recipe 1, Recipe 2")
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    StandardButton(text = "Add", isActive = true, onClick = {})
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
    }
}
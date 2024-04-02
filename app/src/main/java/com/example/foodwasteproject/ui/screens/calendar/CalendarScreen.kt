package com.example.foodwasteproject.ui.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
fun CalendarScreen() {
    Column {
        Text("Test")
    }
}
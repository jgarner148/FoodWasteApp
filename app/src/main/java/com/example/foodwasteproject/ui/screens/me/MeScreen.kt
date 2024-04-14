package com.example.foodwasteproject.ui.screens.me

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun MeScreen() {
    val recipes = arrayOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")
    Column {
        Row {
            TileBarLeftText(title = "Hi User", subtitle = null)
        }
        LazyColumn {
            items(recipes){

            }
        }
    }
}
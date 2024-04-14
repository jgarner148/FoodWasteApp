package com.example.foodwasteproject.ui.screens.Cupboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.ramcosta.composedestinations.annotation.Destination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun CupboardLandingScreen() {
    val recipes = arrayOf("MONDAY","TUESDAY","WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY")
    Column {
        Row {
            TileBarLeftTextWithButton(title = "Your Cupboard", subtitle =  null, buttonText = "ADD", buttonAction = {})
        }
        LazyColumn {
            items(recipes){
                Row(modifier = Modifier.padding(10.dp)) {
                    Column {
                        Text(text = it)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Arrow")
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
    }
}
package com.example.foodwasteproject.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodwasteproject.ui.theme.FoodWasteGreen

@Composable
fun SimpleTitleBar(
    leftContent: @Composable ()-> Unit,
    rightContent: @Composable ()-> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(FoodWasteGreen)){
        leftContent()
        Spacer(modifier = Modifier.weight(1f))
        rightContent()
    }
}

@Composable
fun TileBarLeftText(
    title: String,
    subtitle: String?
){
    SimpleTitleBar(
        leftContent = {
            Column {
                Text(text = title, fontSize = 40.sp, color = Color.White)
                if (subtitle != null) {
                    Text(text = subtitle)
                }
            }
        },
        rightContent = {})
}

@Composable
fun TileBarLeftTextWithButton(
    title: String,
    subtitle: String?,
    buttonText: String,
    buttonAction: ()-> Unit
){
    SimpleTitleBar(
        leftContent = {
            Column {
                Text(text = title, fontSize = 40.sp, color = Color.White)
                if (subtitle != null) {
                    Text(text = subtitle)
                }
            }
        },
        rightContent = {
            Column {
                StandardButton(text = buttonText, isActive = true, onClick = buttonAction)
            }
        })
}

@Preview(name = "Title Bar Preview")
@Composable
fun TitleBarPreview(){
    Column(modifier = Modifier
        .width(250.dp)
        .height(500.dp)) {
        TileBarLeftText(title = "Test", subtitle = "Subtest")
        TileBarLeftTextWithButton(title = "Test", subtitle = "Subtest", buttonText = "button") {
            
        }
    }
}
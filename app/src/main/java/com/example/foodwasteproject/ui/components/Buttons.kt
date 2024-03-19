package com.example.foodwasteproject.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import com.example.foodwasteproject.ui.theme.Madimi


@Composable
fun StandardButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
) = Button(
    onClick = onClick,
    enabled = isActive,
    shape = RoundedCornerShape(15.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = FoodWasteGreen,
        contentColor = Color.White,
        disabledContainerColor = Color.Gray,
        disabledContentColor = Color.Black)
) {
    Text(
        text = text,
        fontFamily = Madimi
    )
}

@Preview(name = "Standard Button Preview")
@Composable
fun StandardButtonPreview(){
    Column() {
        StandardButton(text = "Text", isActive = true, onClick = {})
        StandardButton(text = "Text", isActive = false, onClick = {})
    }
}
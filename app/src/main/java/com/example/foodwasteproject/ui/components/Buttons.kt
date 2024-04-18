package com.example.foodwasteproject.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
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
    modifier: Modifier = Modifier
) = Button(
    modifier = modifier,
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

@Composable
fun TransparentButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) = Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
    shape = RoundedCornerShape(0.dp),
    content = content
)

@Composable
fun TitleBarButton(
    text: String,
    isActive: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) = Button(
    modifier = modifier,
    onClick = onClick,
    enabled = isActive,
    shape = RoundedCornerShape(15.dp),
    colors = ButtonDefaults.buttonColors(
        containerColor = Color.White,
        contentColor = Color.Black,
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
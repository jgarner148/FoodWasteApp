package com.example.foodwasteproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.theme.FoodWasteProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodWasteProjectTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    TileBarLeftText(title = "Home", subtitle = null)
    StandardButton(text = "Test", isActive = true) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodWasteProjectTheme {
        Greeting("Android")
    }
}
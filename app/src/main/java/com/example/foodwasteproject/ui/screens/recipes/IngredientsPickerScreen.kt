package com.example.foodwasteproject.ui.screens.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.components.TransparentButton
import com.example.foodwasteproject.ui.theme.FoodWasteGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientAddBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    sheetState: SheetState,
    ingredients: List<Ingredient>
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        TileBarLeftTextWithButton(title = "Pick Ingredient", buttonText = "New") {

        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(ingredients) {
                TransparentButton(onClick = {}) {
                    Text(text = it.name, color = Color.Black)
                    Spacer(modifier = Modifier.weight(1f))
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

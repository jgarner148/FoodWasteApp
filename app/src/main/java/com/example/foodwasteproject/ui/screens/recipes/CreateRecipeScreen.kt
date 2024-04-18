package com.example.foodwasteproject.ui.screens.recipes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.components.TransparentButton
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateRecipeBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    sheetState: SheetState,
    allIngredients: List<Ingredient>,
    createRecipe: (Recipe) -> Unit
){
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        TileBarLeftText(title = "Create Recipe")
        Row(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){
            var title by remember { mutableStateOf("") }
            var description by remember { mutableStateOf("") }
            var ingredients by remember { mutableStateOf(emptyList<Ingredient>()) }

            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Every recipe needs a title, at least one ingredient and a description \r" +
                        "Add ingredients using the plus button, ingredients can also be created from this menu",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text(text = "Title") },
                    modifier = Modifier.padding(top = 10.dp)
                )
                addIngredientsCell(ingredients, allIngredients)
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text(text = "Description") },
                    minLines = 10
                )
                StandardButton(text = "Create", isActive = true, modifier = Modifier.padding(top = 35.dp), onClick = {
                    onClick()
                    createRecipe(
                        Recipe(
                        "1",
                        title = title,
                        ingredients = allIngredients,
                        description = description
                    )
                    )
                                                                                                                     },)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun addIngredientsCell(
    ingredients: List<Ingredient>,
    allIngredients: List<Ingredient>
) {

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = makeIngredientHeader(ingredients),
            fontSize = 20.sp,
        )
        StandardButton(text = "Add", isActive = true, onClick = {showBottomSheet = true}, modifier = Modifier.padding(top = 5.dp))
        if (showBottomSheet) {
            IngredientAddBottomSheet(
                onDismissRequest = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                sheetState = sheetState,
                listOf(
                    Ingredient("12345", "Cheese"),
                    Ingredient("12345", "Peppers"),
                    Ingredient("12345", "Salt")
                )
            )
        }
    }
}

fun makeIngredientHeader(list: List<Ingredient>) : String{
    if(list.isEmpty()){
        return "Ingredients: empty"
    }
    else{
        return "ingredients" + list.map { it.name }
    }
}
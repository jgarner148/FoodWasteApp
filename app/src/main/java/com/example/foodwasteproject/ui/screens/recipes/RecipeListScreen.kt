package com.example.foodwasteproject.ui.screens.recipes

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.viewmodels.ArticlesHomeScreenViewModel
import com.example.foodwasteproject.engine.viewmodels.RecipesViewModel
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.screens.destinations.RecipeListScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun RecipeListScreen(){
    val viewModel = getViewModel<RecipesViewModel>()
    RecipeListScreenContent(allRecipes = viewModel.allRecipes, allIngredients = viewModel.allIngredients, createRecipe = {viewModel.addRecipeToDatabase(it)})
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeListScreenContent(
    allRecipes: List<Recipe>,
    allIngredients: List<Ingredient>,
    createRecipe: (Recipe) -> Unit
) {
    Column {
        val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val scope = rememberCoroutineScope()
        var showBottomSheet by remember {
            mutableStateOf(false)
        }
        TileBarLeftTextWithButton(title = "Your Recipes", subtitle = null, buttonText = "ADD", buttonAction = {showBottomSheet = true})

        LazyColumn {
            items(allRecipes){
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = it.title)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    StandardButton(text = "View", isActive = true, onClick = {})
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
        if (showBottomSheet) {
            CreateRecipeBottomSheet(
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
                allIngredients = allIngredients,
                createRecipe =  createRecipe
            )
        }
    }
}

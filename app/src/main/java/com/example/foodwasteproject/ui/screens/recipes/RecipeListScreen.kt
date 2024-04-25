package com.example.foodwasteproject.ui.screens.recipes

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.viewmodels.ArticlesHomeScreenViewModel
import com.example.foodwasteproject.engine.viewmodels.RecipesViewModel
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.screens.destinations.RecipeListScreenDestination
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun RecipeListScreen(){
    val viewModel = getViewModel<RecipesViewModel>()
    RecipeListScreenContent(
        allRecipes = viewModel.allRecipes,
        allIngredients = viewModel.allIngredients,
        createRecipe = {viewModel.addRecipeToDatabase(it)},
        deleteRecipe = {viewModel.deleteRecipe(it)},
        allIDs = viewModel.allIDs,
        makeIngredients = {viewModel.createMultipleIngredients(it)}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeListScreenContent(
    allRecipes: List<Recipe>,
    allIngredients: List<Ingredient>,
    createRecipe: (Recipe) -> Unit,
    deleteRecipe: (Recipe) -> Unit,
    allIDs: List<String>,
    makeIngredients: (List<String>) -> Unit
) {
    Column {
        val addSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val viewSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
        val scope = rememberCoroutineScope()
        var showwAddBottomSheet by remember {
            mutableStateOf(false)
        }
        var showwViewBottomSheet by remember {
            mutableStateOf(false)
        }
        var currentRecipe by remember {
            mutableStateOf(Recipe("","", emptyList(), ""))
        }
        TileBarLeftTextWithButton(title = "Your Recipes", subtitle = null, buttonText = "ADD", buttonAction = {showwAddBottomSheet = true})

        LazyColumn {
            items(allRecipes){
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = it.title)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    StandardButton(text = "View", isActive = true, onClick = {
                        currentRecipe = it
                        showwViewBottomSheet = true
                    })
                }
                Divider(thickness = 1.dp, color = Color.Black)
                if (showwViewBottomSheet) {
                    ViewRecipeBottomSheet(
                        onDismissRequest = {
                            scope.launch { viewSheetState.hide() }.invokeOnCompletion {
                                if (!viewSheetState.isVisible) {
                                    showwViewBottomSheet = false
                                }
                            }
                        },
                        onClick = {
                            deleteRecipe(currentRecipe)
                            scope.launch { viewSheetState.hide() }.invokeOnCompletion {
                                if (!viewSheetState.isVisible) {
                                    showwViewBottomSheet = false
                                }
                            }
                        },
                        sheetState = viewSheetState,
                        recipe = currentRecipe
                    )
                }
            }
        }
        if (showwAddBottomSheet) {
            CreateRecipeBottomSheet(
                onDismissRequest = {
                    scope.launch { addSheetState.hide() }.invokeOnCompletion {
                        if (!addSheetState.isVisible) {
                            showwAddBottomSheet = false
                        }
                    }
                },
                onClick = {
                    scope.launch { addSheetState.hide() }.invokeOnCompletion {
                        if (!addSheetState.isVisible) {
                            showwAddBottomSheet = false
                        }
                    }
                },
                sheetState = addSheetState,
                allIngredients = allIngredients,
                createRecipe =  createRecipe,
                allIDs = allIDs,
                makeIngredients = makeIngredients
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ViewRecipeBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    sheetState: SheetState,
    recipe: Recipe
){
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        TileBarLeftText(title = recipe.title)
        Row(modifier = Modifier
            .background(Color.White)){
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Text(text = "Ingredients: ", fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier.padding(5.dp))
                Text(text = recipe.ingredients.map { it.name }.joinToString(), textAlign = TextAlign.Center)
                Text(text = "Description: ", fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier.padding(5.dp))
                Text(text = recipe.description, textAlign = TextAlign.Center)
                StandardButton(text = "Delete", isActive = true, onClick = { onClick() }, modifier = Modifier.padding(5.dp))
            }
        }
    }
}

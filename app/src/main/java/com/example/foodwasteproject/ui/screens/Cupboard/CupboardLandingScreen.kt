package com.example.foodwasteproject.ui.screens.Cupboard

import android.annotation.SuppressLint
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
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.viewmodels.CupboardScreenViewModel
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.components.TitleBarLeftTextWithImageButton
import com.example.foodwasteproject.ui.screens.recipes.IngredientAddBottomSheet
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun CupboardLandingScreen() {
    val viewModel = getViewModel<CupboardScreenViewModel>()
    CupboardLandingScreenContent(
        ingredientList = viewModel.allCupboardIngredients,
        allUserIngreditents = viewModel.allUsersIngredients,
        deleteItem = {viewModel.deleteIngredient(it)},
        createItem = {viewModel.createIngredient(it)},
        addItem = { viewModel.addIngredient(it)},
        makeIngredients = {viewModel.createMultipleIngredients(it)}
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupboardLandingScreenContent(
    ingredientList: List<Ingredient>,
    allUserIngreditents: List<Ingredient>,
    deleteItem: (Ingredient) -> Unit,
    createItem: (String) -> Unit,
    addItem: (Ingredient) -> Unit,
    makeIngredients: (List<String>) -> Unit
){
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    Column {
        Row {
            TileBarLeftTextWithButton(title = "Cupboard", subtitle =  null, buttonText = "ADD", buttonAction = {showBottomSheet = true})
        }
        LazyColumn {
            if(ingredientList.isNotEmpty()) {
                items(ingredientList) {
                    Row(modifier = Modifier.padding(10.dp)) {
                        Column {
                            Text(text = it.name)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        StandardButton(
                            text = "Delete",
                            isActive = true,
                            onClick = { deleteItem(it) })
                    }
                    Divider(thickness = 1.dp, color = Color.Black)
                }
            }else{
                item {
                    Row(modifier = Modifier.fillMaxWidth().padding(10.dp)){
                        Text(text = "USE THE ADD BUTTON TO ADD CURRENT RECIPES TO YOUR CUPBOARD",
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            color = FoodWasteGreen
                        )
                    }
                }
            }
        }
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
                          createItem(it)
                },
                sheetState = sheetState,
                ingredients = allUserIngreditents,
                onAdd = {
                    addItem(it)
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                makeIngredients = makeIngredients
            )
        }
    }
}




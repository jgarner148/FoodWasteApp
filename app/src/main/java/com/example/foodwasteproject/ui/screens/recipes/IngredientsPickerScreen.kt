package com.example.foodwasteproject.ui.screens.recipes

import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.components.TitleBarLeftTextWithImageButton
import com.example.foodwasteproject.ui.screens.CameraScreenLauncher
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientAddBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: (String) -> Unit,
    onAdd: (Ingredient)-> Unit,
    sheetState: SheetState,
    ingredients: List<Ingredient>,
    makeIngredients: (List<String>) -> Unit
) {
    val sheetState2 = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val postCameraSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember {
        mutableStateOf(false)
    }
    var showPostCameraBottomSheet by remember {
        mutableStateOf(false)
    }
    var detectedText by remember { mutableStateOf("") }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        TileBarLeftTextWithButton(title = "Pick Ingredient", buttonText = "New", buttonAction = { showBottomSheet = true })
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(ingredients) {
                Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = it.name)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    StandardButton(text = "Add", isActive = true, onClick = {
                        onAdd(it)
                    })
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        if (showBottomSheet) {
            createIngredientBottomSheet(
                onDismissRequest = {
                    scope.launch { sheetState2.hide() }.invokeOnCompletion {
                        if (!sheetState2.isVisible) {
                            showBottomSheet = false
                        }
                    }
                },
                onClick = {
                    scope.launch { sheetState2.hide() }.invokeOnCompletion {
                        if (!sheetState2.isVisible) {
                            showBottomSheet = false
                        }
                    }
                    onClick(it)
                },
                currentSheetState = sheetState2,
                updatedText = {
                    detectedText = it
                    showPostCameraBottomSheet = true
                }
            )
        }
        if (showPostCameraBottomSheet) {
            postCameraBottomSheet(
                onDismissRequest = {
                    scope.launch { postCameraSheetState.hide() }.invokeOnCompletion {
                        if (!postCameraSheetState.isVisible) {
                            showPostCameraBottomSheet = false
                        }
                    }
                },
                makeIngredients = {
                    makeIngredients(it)
                    scope.launch { postCameraSheetState.hide() }.invokeOnCompletion {
                        if (!postCameraSheetState.isVisible) {
                            showPostCameraBottomSheet = false
                        }
                    }
                },
                sheetState = postCameraSheetState,
                detectedText = detectedText
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createIngredientBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: (String) -> Unit,
    currentSheetState: SheetState,
    updatedText: (String) -> Unit,
)
{
    val cameraSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()
    var showCameraBottomSheet by remember {
        mutableStateOf(false)
    }
    var showPostCameraBottomSheet by remember {
        mutableStateOf(false)
    }
    var name by remember { mutableStateOf("") }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = currentSheetState,
        containerColor = FoodWasteGreen
    ) {
        TitleBarLeftTextWithImageButton(title = "Create", buttonImage = {Image(painter = painterResource(id = R.drawable.baseline_photo_camera_24), contentDescription = "Test")}, buttonAction = {showCameraBottomSheet = true} )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                modifier = Modifier.padding(top = 10.dp)
            )
            StandardButton(text = "Add", isActive = true, onClick = { onClick(name) })
        }
        Spacer(modifier = Modifier.weight(1f))
        if (showCameraBottomSheet) {
            computerVisionIngredientBottomSheet(
                onDismissRequest = {
                    scope.launch { cameraSheetState.hide() }.invokeOnCompletion {
                        if (!cameraSheetState.isVisible) {
                            showCameraBottomSheet = false
                        }
                    }
                },
                onClick = {
                    onDismissRequest()
                    updatedText(it)
                    showPostCameraBottomSheet = true
                    scope.launch { cameraSheetState.hide() }.invokeOnCompletion {
                        if (!cameraSheetState.isVisible) {
                            showCameraBottomSheet = false
                        }
                    }
                },
                sheetState = cameraSheetState,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun computerVisionIngredientBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: (String) -> Unit,
    sheetState: SheetState,
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            CameraScreenLauncher(onClick = onClick)
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun postCameraBottomSheet(
    onDismissRequest: () -> Unit,
    makeIngredients: (List<String>) -> Unit,
    sheetState: SheetState,
    detectedText: String
) {
    var selected by remember { mutableStateOf(emptyList<String>().toMutableList()) }
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val result = detectedText.split("\n").map { it.trim() }
            TileBarLeftText(title = "Select ingredients to add")
            LazyColumn {
                items(result){ item ->
                    var checked by remember {
                        mutableStateOf(false)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Checkbox(
                            checked = checked, onCheckedChange = {
                            checked = !checked
                            if(checked){
                                selected.add(item)
                            }else{
                                selected.remove(item)
                            }
                        }, colors = CheckboxDefaults.colors(
                            checkedColor = FoodWasteGreen
                        )
                        )
                        Text(text = item)
                    }
                }
                item {
                    StandardButton(text = "Add Selected", isActive = true, onClick = { makeIngredients(selected) })
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
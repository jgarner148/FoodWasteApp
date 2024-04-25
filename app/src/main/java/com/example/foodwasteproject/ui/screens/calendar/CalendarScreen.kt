package com.example.foodwasteproject.ui.screens.calendar

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.engine.objects.Calendar
import com.example.foodwasteproject.engine.objects.CalendarDay
import com.example.foodwasteproject.engine.objects.Recipe
import com.example.foodwasteproject.engine.objects.toSubtitle
import com.example.foodwasteproject.engine.viewmodels.CalendarScreenViewModel
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.components.TileBarLeftTextRightDate
import com.example.foodwasteproject.ui.components.TileBarLeftTextWithButton
import com.example.foodwasteproject.ui.components.TransparentButton
import com.example.foodwasteproject.ui.screens.articles.ArticleViewBottomSheet
import com.example.foodwasteproject.ui.theme.FoodWasteGreen

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
fun CalendarScreen() {
    val viewModel = getViewModel<CalendarScreenViewModel>()
    CalendarScreenContent(wholeCalendar = viewModel.wholeCalendar, viewModel.allRecipes, randomRecipe = viewModel.generateRecipeSuggestion(), addTo = {
        recipe: Recipe, day: Long ->
        viewModel.addRecipe(
            recipe,
            day
        )
    }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreenContent(
    wholeCalendar: Calendar,
    allRecipes: List<Recipe>,
    addTo: (Recipe, Long) -> Unit,
    randomRecipe: Recipe
) {
    Column {
        Row {
            TileBarLeftTextRightDate(
                title = "Calendar",
                subtitle = null,
                startDate = wholeCalendar.startDate,
                endDate = wholeCalendar.endDate
            )
        }
        Row(modifier = Modifier.fillMaxWidth()){
            Text(
                text = if (randomRecipe.title != "") {
                    "Have you considered adding " + randomRecipe.title + " to your calendar this week?"
                } else {
                    "Add some recipes to get started"
                },
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(5.dp),
                color = FoodWasteGreen
            )
        }
        LazyColumn {
            items(wholeCalendar.days) {
                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember {
                    mutableStateOf(false)
                }
                Row(modifier = Modifier.padding(10.dp)) {
                    val recipes = remember { mutableStateOf(it.recipes.toSubtitle()) }
                    Column {
                        Text(text = it.name, fontWeight = FontWeight.Bold, fontSize = 30.sp)
                        Text(text = recipes.value, fontStyle = FontStyle.Italic)
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    StandardButton(
                        text = "Add",
                        isActive = true,
                        onClick = { showBottomSheet = true })
                    if (showBottomSheet) {
                        CalendarAddBottomSheet(
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
                            recipes = allRecipes,
                            addTo = addTo,
                            day = it.id
                        )
                    }
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarAddBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    sheetState: SheetState,
    recipes: List<Recipe>,
    addTo: (Recipe, Long) -> Unit,
    day: Long
){
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        TileBarLeftText(title = "Add Recipe")
        Row(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)){
            LazyColumn {
                items(recipes){
                    Row (modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(text = it.title, fontSize = 20.sp)
                        Spacer(modifier = Modifier.weight(1f))
                        StandardButton(text = "add", isActive = true, onClick = {
                            onClick()
                            addTo(it, day)
                        })
                    }
                }
            }

        }
        Spacer(modifier = Modifier.weight(1f))
    }
}


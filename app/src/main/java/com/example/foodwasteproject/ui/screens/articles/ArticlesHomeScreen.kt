package com.example.foodwasteproject.ui.screens.articles

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.engine.objects.Ingredient
import com.example.foodwasteproject.engine.viewmodels.ArticlesHomeScreenViewModel
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.components.TransparentButton
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun ArticlesHomeScreen() {
    val viewModel = getViewModel<ArticlesHomeScreenViewModel>()
    ArticlesHomeScreenContent(
        viewModel.allArticles
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticlesHomeScreenContent(
    allArticles: List<Article>
) {
    Column {
        Row {
            TileBarLeftText(title = "Articles", subtitle = null)
        }
        LazyColumn{
            items(allArticles) {
                val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember {
                    mutableStateOf(false)
                }
                TransparentButton(onClick = {showBottomSheet = true }) {
                    Row{
                        Box(modifier = Modifier.padding(end = 10.dp)) {
                            AsyncImage(model = it.thumbnailURL, contentDescription = "TEST")
                        }
                        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                            Text(text = it.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Text(text = it.subtitle, color = Color.Black, maxLines = 2, overflow = TextOverflow.Ellipsis)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = it.publishDate, color = Color.Gray)
                    }
                    if (showBottomSheet) {
                        ArticleViewBottomSheet(
                            onDismissRequest = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }},
                            onClick = {scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }},
                            sheetState = sheetState,
                            article = it
                            )
                    }
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
    }
}


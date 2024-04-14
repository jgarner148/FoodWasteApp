package com.example.foodwasteproject.ui.screens.articles

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.engine.viewmodels.ArticlesHomeScreenViewModel
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.components.TransparentRowButton
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@Destination
fun ArticlesHomeScreen() {
    val viewModel = getViewModel<ArticlesHomeScreenViewModel>()
    ArticlesHomeScreenContent(viewModel.allArticles)
}


@Composable
fun ArticlesHomeScreenContent(
    allArticles: List<Article>
) {
    Column {
        Row {
            TileBarLeftText(title = "Articles", subtitle = null)
        }
        LazyColumn {
            items(allArticles) {
//                val sheetState = rememberModalBottomSheetState()
//                val scope = rememberCoroutineScope()
//                var showBottomSheet by remember {
//                    mutableStateOf(false)
//                }
                TransparentRowButton(onClick = { }) {
                    Row {
                        Box(modifier = Modifier.padding(end = 10.dp)) {
                            AsyncImage(model = it.thumbnailURL, contentDescription = "TEST")
                        }
                        Column {
                            Text(text = it.title, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.Black)
                            Text(text = it.subtitle, color = Color.Black)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = it.publishDate, color = Color.Gray)
                    }
//                    while (showBottomSheet) {
//                        ModalBottomSheet(
//                            onDismissRequest = {
//                                showBottomSheet = false
//                            }
//                        ) {
//                            Button(onClick = {
//                                showBottomSheet = false
//                            }) {
//                                Text("Hide bottom sheet")
//                            }
//
//                        }
//                    }
                }
                Divider(thickness = 1.dp, color = Color.Black)
            }
        }
    }
}
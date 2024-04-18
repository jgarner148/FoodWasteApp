package com.example.foodwasteproject.ui.screens.articles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.foodwasteproject.R
import com.example.foodwasteproject.engine.objects.Article
import com.example.foodwasteproject.ui.components.TransparentButton
import com.example.foodwasteproject.ui.theme.FoodWasteGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleViewBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
    sheetState: SheetState,
    article: Article
){
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = FoodWasteGreen
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TransparentButton(onClick = onClick) {
                Image(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Test")
            }
            Column(modifier = Modifier.padding(end = 10.dp, bottom = 10.dp)) {
                Row {
                    Text(text = article.title, fontSize = 40.sp)
                }
                Row {
                    Text(text = article.subtitle, fontSize = 20.sp,
                        modifier = Modifier.fillMaxWidth(0.6f))
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = article.publishDate, fontSize = 20.sp, color = Color.LightGray)
                }
            }
        }
        AsyncImage(model = article.bannerImageURL, contentDescription = "TEST")
        Row(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)) {
            LazyColumn {
                item {
                    Text(
                        text = article.content,
                        color = Color.Black,
                        modifier = Modifier.padding(20.dp)
                    )
                }

            }
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
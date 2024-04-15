package com.example.foodwasteproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.foodwasteproject.engine.database.LocalDatabase
import com.example.foodwasteproject.engine.objects.ArticleDao
import com.example.foodwasteproject.engine.objects.CalendarDao
import com.example.foodwasteproject.engine.objects.CalendarDayDao
import com.example.foodwasteproject.engine.viewmodels.ArticlesHomeScreenViewModel
import com.example.foodwasteproject.engine.viewmodels.CalendarScreenViewModel
import com.example.foodwasteproject.ui.components.BottomBar
import com.example.foodwasteproject.ui.components.StandardButton
import com.example.foodwasteproject.ui.components.TileBarLeftText
import com.example.foodwasteproject.ui.screens.NavGraphs
import com.example.foodwasteproject.ui.theme.FoodWasteProjectTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.defaults.NestedNavGraphDefaultAnimations
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations
import com.ramcosta.composedestinations.rememberNavHostEngine
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostEngine = rememberNavHostEngine()
            val navController = rememberNavController()
            val localDatabase = Room.databaseBuilder(
                applicationContext,
                LocalDatabase::class.java, "database-name"
            ).build()

            startKoin{
                modules(modules = module{
                    single<ArticleDao>{localDatabase.articleDao()}
                    single<CalendarDao>{localDatabase.calendarDao()}
                    single<CalendarDayDao>{localDatabase.calendarDayDao()}
                    viewModel{ArticlesHomeScreenViewModel(get())}
                    viewModel{CalendarScreenViewModel(get())}
                })
            }
            Scaffold(bottomBar = { BottomBar(navController = navController) }) {
                DestinationsNavHost(navGraph = NavGraphs.root, navController = navController, engine = navHostEngine)
            }
        }
    }
}
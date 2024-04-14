package com.example.foodwasteproject.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodwasteproject.R
import com.example.foodwasteproject.ui.screens.NavGraphs
import com.example.foodwasteproject.ui.screens.appCurrentDestinationAsState
import com.example.foodwasteproject.ui.screens.destinations.ArticlesHomeScreenDestination
import com.example.foodwasteproject.ui.screens.destinations.CalendarScreenDestination
import com.example.foodwasteproject.ui.screens.destinations.CupboardLandingScreenDestination
import com.example.foodwasteproject.ui.screens.destinations.MeScreenDestination
import com.example.foodwasteproject.ui.screens.destinations.RecipeListScreenDestination
import com.example.foodwasteproject.ui.screens.destinations.TypedDestination
import com.example.foodwasteproject.ui.screens.startAppDestination
import com.example.foodwasteproject.ui.theme.FoodWasteGreen
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec


enum class BottomNavDestination(
    val direction: DirectionDestinationSpec,
    val position: Int
) {
    Recipes(
        RecipeListScreenDestination,
        1
    ),
    Cupboard(
        CupboardLandingScreenDestination,
        2
    ),
    Calendar(
        CalendarScreenDestination,
        3
    ),
    Articles(
        ArticlesHomeScreenDestination,
        4
    ),
    Me(
        MeScreenDestination,
        5
    ),
    ;
    @Composable
    fun icon(): Int = when (this) {
        Recipes -> R.drawable.baseline_receipt_24
        Cupboard -> R.drawable.baseline_space_dashboard_24
        Calendar -> R.drawable.baseline_calendar_today_24
        Articles -> R.drawable.baseline_description_24
        Me -> R.drawable.baseline_receipt_24
    }

    fun label(): String = when (this) {
        Recipes -> "Recipes"
        Cupboard -> "Cupboard"
        Calendar -> "Calendar"
        Articles -> "Articles"
        Me -> "Me"
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier.navigationBarsPadding(),
    navController: NavController
) {
    val currentDestination = navController.currentDestination

    NavigationBar(
        containerColor = FoodWasteGreen,
        contentColor = FoodWasteGreen
    ) {
        BottomNavDestination.values().forEach { destination ->
            NavigationBarItem(
                modifier = modifier,
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = { Image(painter = painterResource(id = destination.icon()), contentDescription = "Test") },
                label = { Text(text =destination.label(), color = Color.White) },
            )
        }
    }
}
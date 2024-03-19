package com.example.foodwasteproject

import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodwasteproject.ui.components.BottomNavItem
import com.example.foodwasteproject.ui.screens.calendar.CalendarScreen

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        BottomNavItem.Articles,
        BottomNavItem.Calendar,
        BottomNavItem.Cupboard,
        BottomNavItem.Me,
        BottomNavItem.Recipes,
    )
    Scaffold(
        bottomBar = {
            //
        }
    ) {
        MainScreenNavigationConfigurations(navController)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController
) {
    NavHost(navController, startDestination = BottomNavItem.Recipes.route) {
        composeable(BottomNavItem.Recipes.route) {
            CalendarScreen()
        }
        composable(BottomNavItem.Articles.route) {
            CalendarScreen()
        }
        composable(BottomNavItem.Calendar.route) {
            CalendarScreen()
        }
        composable(BottomNavItem.Cupboard.route) {
            CalendarScreen()
        }
        composable(BottomNavItem.Me.route) {
            CalendarScreen()
        }
    }
}
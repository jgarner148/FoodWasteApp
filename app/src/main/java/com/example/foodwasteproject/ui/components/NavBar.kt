package com.example.foodwasteproject.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Recipes : BottomNavItem("recipes", Icons.Default.Home, "Recipes")
    data object Cupboard : BottomNavItem("cupboard", Icons.Default.Home, "Cupboard")
    data object Calendar : BottomNavItem("calendar", Icons.Default.Home, "Calendar")
    data object Articles : BottomNavItem("articles", Icons.Default.Home, "Articles")
    data object Me : BottomNavItem("me", Icons.Default.Home, "Me")
}
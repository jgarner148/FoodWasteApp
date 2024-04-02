package com.example.foodwasteproject.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.foodwasteproject.ui.screens.calendar.NavGraphs
import com.example.foodwasteproject.ui.screens.calendar.appCurrentDestinationAsState
import com.example.foodwasteproject.ui.screens.calendar.destinations.CalendarScreenDestination
import com.example.foodwasteproject.ui.screens.calendar.destinations.Destination
import com.example.foodwasteproject.ui.screens.calendar.startAppDestination
import com.ramcosta.composedestinations.DestinationsNavHost
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

class RootDestinationsNavigator(
    private val navController: DestinationsNavigator
) : DestinationsNavigator by navController

@RootNavGraph
@NavGraph
annotation class BottomNavigationBarGraph(
    val start: Boolean = false
)

enum class BottomNavDestination(
    val direction: DirectionDestinationSpec,
    val position: Int
) {
    Calendar(
        CalendarScreenDestination,
        1
    ),
    Budgets(
        CalendarScreenDestination,
        2
    ),
    More(
        CalendarScreenDestination,
        4
    ),
    Suppliers(
        CalendarScreenDestination,
        3
    ),
    ;
    @Composable
    fun icon(): ImageVector = when (this) {
        Calendar -> ImageVector.vectorResource(id = androidx.core.R.drawable.notification_bg)
        Budgets -> ImageVector.vectorResource(id = androidx.core.R.drawable.notification_bg)
        Suppliers -> ImageVector.vectorResource(id = androidx.core.R.drawable.notification_bg)
        More -> ImageVector.vectorResource(id = androidx.core.R.drawable.notification_bg)
    }

    fun label(): String = when (this) {
        Calendar -> "Test1"
        Budgets -> "Test2"
        Suppliers -> "Test3"
        More -> "Test4"
    }
}

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
//@com.ramcosta.composedestinations.annotation.Destination
//@Composable
//fun MainBottomNavigationHost(
//    rootNavController: DestinationsNavigator
//) {
//    val navController = rememberNavController()
//    val navHostController = rememberNavHostEngine()
//
//    Scaffold(
//
//        bottomBar = {
//            BottomBar(
//                modifier = Modifier.navigationBarsPadding(),
//                navController = navController
//            )
//        }
//    ) {
//        DestinationsNavHost(
//            navController = navController,
//            navGraph = NavGraphs.root
////            engine = navHostController,
////            dependenciesContainerBuilder = {
////                dependency(NavGraphs) {
////                    RootDestinationsNavigator(rootNavController)
////                }
////            }
//        )
//    }
//}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier.navigationBarsPadding(),
    navController: NavController
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    BottomNavigation {
        BottomNavDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigate(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = { },
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}


@Composable
private fun BottomBarContent(
    modifier: Modifier = Modifier,
    currentDestination: Destination,
    onBottomBarItemClick: (Direction) -> Unit
) {
    NavigationBar(
        modifier = modifier
            .height(64.dp)
            .padding(0.dp),

        ) {
        BottomNavDestination.entries.sortedBy { it.position }.forEach { destination ->
            val isSelected = currentDestination == destination.direction
            val selectionColor = if (isSelected) Color.Black else Color.Red
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val indicatorColor = when {
                    isSelected -> Color.Black
                    else -> Color.Red
                }
                this@NavigationBar.NavigationBarItem(
                    modifier = Modifier.fillMaxWidth(),
                    icon = {
                        if (destination == BottomNavDestination.Calendar) {
                            Box {
//                                Icon(
//                                    modifier = Modifier.size(32.dp),
//                                    imageVector = destination.icon(),
//                                    contentDescription = destination.label(),
//                                    tint = selectionColor
//                                )
//                                Icon(
//                                    modifier = Modifier.size(32.dp),
//                                    imageVector = destination.icon(),
//                                    contentDescription = destination.label(),
//                                )
                            }
                        } else {
//                            Icon(
//                                modifier = Modifier.size(32.dp),
//                                imageVector = destination.icon(),
//                                contentDescription = destination.label(),
//                                tint = selectionColor
//                            )
                        }
                    },
                    label = {
                        Text(
                            text = destination.label(),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 12.sp,
                            color = selectionColor
                        )
                    },
                    selected = isSelected,
                    onClick = {
                        onBottomBarItemClick(destination.direction)
                    },

                    )
            }
        }
    }
}


//sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
//    data object Recipes : BottomNavItem("recipes", Icons.Default.Home, "Recipes")
//    data object Cupboard : BottomNavItem("cupboard", Icons.Default.Home, "Cupboard")
//    data object Calendar : BottomNavItem("calendar", Icons.Default.Home, "Calendar")
//    data object Articles : BottomNavItem("articles", Icons.Default.Home, "Articles")
//    data object Me : BottomNavItem("me", Icons.Default.Home, "Me")
//}


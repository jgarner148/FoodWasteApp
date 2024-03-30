package com.example.foodwasteproject.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import com.ramcosta.composedestinations.spec.Direction
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

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
//    Calendar(
//        CalendarScreenDestination,
//        1
//    ),
//    Budgets(
//        BudgetsScreenDestination,
//        2
//    ),
//    More(
//        MoreScreenDestination,
//        4
//    ),
//    Suppliers(
//        SuppliersScreenDestination,
//        3
//    ),
//    ;
//    @Composable
//    fun icon(): ImageVector = when (this) {
//        Approvals -> ImageVector.vectorResource(id = R.drawable.approvals_icon)
//        Budgets -> ImageVector.vectorResource(id = R.drawable.budgets_icon)
//        Suppliers -> ImageVector.vectorResource(id = R.drawable.suppliers_icon)
//        More -> ImageVector.vectorResource(id = R.drawable.more_icon)
//    }
//
//    @Composable
//    fun label(): String = when (this) {
//        Approvals -> MR.strings.bottom_nav_approvals
//        Budgets -> MR.strings.bottom_nav_budgets
//        Suppliers -> MR.strings.bottom_nav_suppliers
//        More -> MR.strings.bottom_nav_more
//    }.resource()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@com.ramcosta.composedestinations.annotation.Destination
@Composable
fun MainBottomNavigationHost(
    rootNavController: DestinationsNavigator
) {
    val navController = rememberAnimatedNavController()
    val navHostController = rememberAnimatedNavHostEngine()

    Scaffold(

        bottomBar = {
            BottomBar(
                modifier = Modifier.navigationBarsPadding(),
                navController = navController
            )
        }
    ) {
//        DestinationsNavHost(
//            navController = navController,
//            navGraph = NavGraphs.bottomNavigationBarGraph,
//            engine = navHostController,
//            dependenciesContainerBuilder = {
//                dependency(NavGraphs.bottomNavigationBarGraph) {
//                    RootDestinationsNavigator(rootNavController)
//                }
//            }
//        )
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier.navigationBarsPadding(),
    navController: NavController
) {
//    val currentDestination = navController.appCurrentDestinationAsState().value
//        ?: NavGraphs.root.startAppDestination
//
//    BottomBarContent(
//        modifier,
//        currentDestination
//    ) { destination ->
//        navController.navigate(destination) {
//            launchSingleTop = true
//        }
//    }
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
//        BottomNavDestination.values().sortedBy { it.position }.forEach { destination ->
//            val isSelected = currentDestination == destination.direction
//            val selectionColor = if (isSelected) ColorPalette.MountainMeadow else ColorPalette.CadetBlue
//            Column(
//                modifier = Modifier.weight(1f),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                val indicatorColor = when {
//                    isSelected -> ColorPalette.MountainMeadow
//                    else -> ColorPalette.CadetBlue
//                }
//                this@NavigationBar.BottomNavigationItem(
//                    modifier = Modifier.fillMaxWidth(),
//                    icon = {
//                        if (destination == BottomNavDestination.Approvals) {
//                            Box {
//                                Icon(
//                                    modifier = Modifier.size(32.dp),
//                                    imageVector = ImageVector.vectorResource(id = R.drawable.approvals_icon),
//                                    contentDescription = destination.label(),
//                                    tint = selectionColor
//                                )
//                                Icon(
//                                    modifier = Modifier.size(32.dp),
//                                    imageVector = ImageVector.vectorResource(id = R.drawable.approvals_icon_top),
//                                    contentDescription = destination.label(),
//                                )
//                            }
//                        } else {
//                            Icon(
//                                modifier = Modifier.size(32.dp),
//                                imageVector = destination.icon(),
//                                contentDescription = destination.label(),
//                                tint = selectionColor
//                            )
//                        }
//                    },
//                    label = {
//                        Text(
//                            text = destination.label(),
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis,
//                            fontSize = 12.sp,
//                            color = selectionColor
//                        )
//                    },
//                    selected = isSelected,
//                    onClick = {
//                        onBottomBarItemClick(destination.direction)
//                    },
//
//                    )
//            }
//        }
    }
}


sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    data object Recipes : BottomNavItem("recipes", Icons.Default.Home, "Recipes")
    data object Cupboard : BottomNavItem("cupboard", Icons.Default.Home, "Cupboard")
    data object Calendar : BottomNavItem("calendar", Icons.Default.Home, "Calendar")
    data object Articles : BottomNavItem("articles", Icons.Default.Home, "Articles")
    data object Me : BottomNavItem("me", Icons.Default.Home, "Me")
}


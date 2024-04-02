package com.example.foodwasteproject


//@Composable
//fun MainScreen() {
//
//    val navController = rememberNavController()
//
//    val bottomNavigationItems = listOf(
//        BottomNavItem.Articles,
//        BottomNavItem.Calendar,
//        BottomNavItem.Cupboard,
//        BottomNavItem.Me,
//        BottomNavItem.Recipes,
//    )
//    Scaffold(
//        bottomBar = {
//            //
//        }
//    ) {
//        MainScreenNavigationConfigurations(navController)
//    }
//}
//
//@Composable
//private fun MainScreenNavigationConfigurations(
//    navController: NavHostController
//) {
//    NavHost(navController, startDestination = BottomNavItem.Recipes.route) {
////        composable(BottomNavItem.Recipes.route) {
//            CalendarScreen()
//        }
//        COMP(BottomNavItem.Articles.route) {
//            CalendarScreen()
//        }
//        composable(BottomNavItem.Calendar.route) {
//            CalendarScreen()
//        }
//        composable(BottomNavItem.Cupboard.route) {
//            CalendarScreen()
//        }
//        composable(BottomNavItem.Me.route) {
//            CalendarScreen()
//        }
//    }
//}
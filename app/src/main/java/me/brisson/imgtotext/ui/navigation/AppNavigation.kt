package me.brisson.imgtotext.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.brisson.imgtotext.ui.screen.main.MainRoute
import me.brisson.imgtotext.ui.screen.result.ResultRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppNavigationRoutes.main_route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(route = AppNavigationRoutes.main_route) {
            MainRoute(onCameraScan = { /*TODO*/ }, onImageScan = { /*TODO*/ })
        }

        composable(route = AppNavigationRoutes.result_route) {
            ResultRoute()
        }
    }
}

package org.d3if0069.miniproject1

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0069.miniproject1.ui.screen.MainScreen
import org.d3if0069.miniproject1.navigation.AboutScreen
import org.d3if0069.miniproject1.navigation.Screen

@Composable
fun SetupNavGrapgh(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ){
        composable(route = Screen.Home.route){
            MainScreen(navController)
        }
        composable(route = Screen.About.route) {
            AboutScreen(navController)
        }
    }
}
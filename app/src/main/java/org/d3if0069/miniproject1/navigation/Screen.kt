package org.d3if0069.miniproject1.navigation

sealed class Screen (val route: String){
    data object Home: Screen("mainScreen")
    data object About: Screen("aboutScreen")
}
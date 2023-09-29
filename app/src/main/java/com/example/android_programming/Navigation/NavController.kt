package com.example.android_programming.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_programming.HomeScreen.HomeScreen
import com.example.android_programming.LikeScreen.LikeScreen
import com.example.android_programming.Screens.OrderScreen
import com.example.android_programming.Screens.ProfileScreen

@Composable
fun NavController(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route
    ){
        composable(NavItem.Home.route){
            HomeScreen()
        }
        composable(NavItem.Like.route){
            LikeScreen()
        }
        composable(NavItem.Order.route){
            OrderScreen()
        }
        composable(NavItem.Profile.route){
            ProfileScreen()
        }
    }
}
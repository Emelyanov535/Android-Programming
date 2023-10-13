package com.example.android_programming.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.android_programming.Screens.AdminPanel.AddPanel
import com.example.android_programming.Screens.AdminPanel.AdminPanel
import com.example.android_programming.Screens.AdminPanel.ChangePanel
import com.example.android_programming.Screens.AdminPanel.ChangeSneaker
import com.example.android_programming.Screens.HomeScreen.AboutSneaker
import com.example.android_programming.Screens.HomeScreen.HomeScreen
import com.example.android_programming.Screens.LikeScreen.LikeScreen
import com.example.android_programming.Screens.OrderScreen.OrderScreen
import com.example.android_programming.Screens.ProfileScreen.Profile.Person
import com.example.android_programming.Screens.ProfileScreen.Profile.ProfileScreen
import com.example.android_programming.Screens.ProfileScreen.SignIn.LoginScreen
import com.example.android_programming.Screens.ProfileScreen.SignUp.SignUpScreen
import com.example.android_programming.SneakerItem
import com.google.gson.Gson

@Composable
fun NavController(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route
    ){
        composable(NavItem.Home.route){
            HomeScreen(navController)
        }
        composable(NavItem.Like.route){
            LikeScreen()
        }
        composable(NavItem.Order.route){
            OrderScreen()
        }
        composable(NavItem.Profile.route){
            ProfileScreen(navController)
        }
        composable(NavItem.SignIn.route){
            LoginScreen()
        }
        composable(NavItem.SignUp.route){
            SignUpScreen()
        }
        composable(NavItem.Person.route){
            Person()
        }
        composable(NavItem.AdminPanel.route){
            AdminPanel(navController)
        }
        composable(NavItem.AddPanel.route){
            AddPanel()
        }
        composable(NavItem.ChangePanel.route){
            ChangePanel(navController)
        }
        composable(NavItem.AboutSneaker.route) { backStackEntry ->
            val sneakerItemString = backStackEntry.arguments?.getString("sneakerItem")
            val sneakerItem = Gson().fromJson(sneakerItemString, SneakerItem::class.java)
            sneakerItem?.let { AboutSneaker(it) }
        }
        composable(NavItem.ChangeSneaker.route) { backStackEntry ->
            val sneakerItemString = backStackEntry.arguments?.getString("sneakerItem")
            val sneakerItem = Gson().fromJson(sneakerItemString, SneakerItem::class.java)
            sneakerItem?.let { ChangeSneaker(it) }
        }
    }
}
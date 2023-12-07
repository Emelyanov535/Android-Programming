package com.example.android_programming.composeui.Screens.ProfileScreen.Profile;

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.android_programming.GlobalUser
import com.example.android_programming.composeui.Screens.ProfileScreen.SignIn.LoginScreen
import com.example.android_programming.model.Basket
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.BasketViewModel

@Composable
fun ProfileScreen(navController: NavHostController) {
    val globalUser: GlobalUser = GlobalUser.getInstance()
    if(globalUser.getUser() != null){
        Person(navController)
    }else{
        LoginScreen(navController = navController)
    }
}

@Composable
@Preview
fun ProfileScreenPreview(){
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}
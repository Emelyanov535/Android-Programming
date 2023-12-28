package com.example.android_programming.composeui.Screens.ProfileScreen.SignIn

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.api.ApiStatus
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.UserViewModel
import com.example.android_programming.composeui.circular

@Composable
fun LoginScreen(navController: NavHostController, userViewModel: UserViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val context = LocalContext.current
    when(userViewModel.apiStatus){
        ApiStatus.LOADING -> circular()
        ApiStatus.ERROR -> Toast.makeText(context, "Не верные данные или пользователя не существует: " + userViewModel.apiError, Toast.LENGTH_SHORT).show()
        else -> {}
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        verticalArrangement = Arrangement.Center
    ) {
        SignInCard(navController)
    }
}
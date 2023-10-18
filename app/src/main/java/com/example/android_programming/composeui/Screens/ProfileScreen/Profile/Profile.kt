package com.example.android_programming.composeui.Screens.ProfileScreen.Profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.android_programming.GlobalUser
import com.example.android_programming.R

@Composable
fun Person(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                GlobalUser.getInstance().setUser(null)
                navHostController.navigate("profile")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = colorResource(id = R.color.white),
                contentColor = Color.Black
            ),
            modifier = Modifier
                .padding(16.dp, 0.dp)
        ) {
            Text("Exit")
        }
        ProfileCard(navHostController)
    }
}
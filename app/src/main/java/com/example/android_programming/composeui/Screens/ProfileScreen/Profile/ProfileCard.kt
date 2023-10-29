package com.example.android_programming.composeui.Screens.ProfileScreen.Profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.android_programming.GlobalUser
import com.example.android_programming.R

@Composable
fun ProfileCard(navHostController: NavHostController) {
    val globalUser = GlobalUser.getInstance().getUser()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(id = R.color.figma))
    ){
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Image(
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.shailushai),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp)
                    .border(2.dp, Color.White, CircleShape)
                    .padding(16.dp)
                    .clip(CircleShape)
            )

            if (globalUser != null) {
                Text(
                    text = "${globalUser.name} ${globalUser.surname}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = globalUser.email,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.figma_blue),
                    contentColor = Color.White
                ),
                onClick = {
                    navHostController.navigate("myorder")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 0.dp)
            ) {
                Text("My order")
            }
        }
    }
}
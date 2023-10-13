package com.example.android_programming.Screens.AdminPanel

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ButtonAdmin(onAddClick: () -> Unit, onChangeClick: () -> Unit) {
    Row(){
        Button(
            onClick = {
                onAddClick()
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(0.5f)
                .padding(16.dp)
        ) {
            Text("Add")
        }
        Button(
            onClick = {
                onChangeClick()
            },
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Change/Del")
        }
    }
}
package com.example.android_programming.composeui.Screens.AdminPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AdminPanel(navHostController: NavHostController) {
    var isAddPanelVisible by remember { mutableStateOf(false) }
    var isChangePanelVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(bottom = 50.dp)
    ) {
        ButtonAdmin(
            onAddClick = {
                isAddPanelVisible = true
                isChangePanelVisible = false
            },
            onChangeClick = {
                isChangePanelVisible = true
                isAddPanelVisible = false
            }
        )

        if (isAddPanelVisible) {
            AddPanel()
        }

        if (isChangePanelVisible) {
            ChangePanel(navHostController)
        }
    }
}
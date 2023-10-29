package com.example.android_programming.composeui.Screens.AdminPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.vmodel.SneakerViewModel

@Composable
fun ChangePanel(navHostController: NavHostController, sneakerViewModel: SneakerViewModel = viewModel(factory = SneakerViewModel.factory)) {
    val list = sneakerViewModel.SneakerList.collectAsState(initial = emptyList()).value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        Row {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                itemsIndexed(list
                ){_, item->
                    CardSneakerForChange(item = item, navHostController)
                }
            }
        }
    }
}
package com.example.android_programming.composeui.Screens.AdminPanel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.android_programming.model.Sneaker
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.SneakerViewModel
import kotlinx.coroutines.delay

@Composable
fun ChangePanel(navHostController: NavHostController, sneakerViewModel: SneakerViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
//    val list = sneakerViewModel.sneakerList.collectAsLazyPagingItems()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp, 80.dp)
    ) {
//        Row {
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                items(list.itemCount) { index ->
//                    list[index]?.let { sneaker ->
//                        CardSneakerForChange(item = sneaker, navController = navHostController)
//                    }
//                }
//            }
//        }
    }
}



package com.example.android_programming.composeui.Screens.HomeScreen.SneakerRecyclerView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.vmodel.AppViewModelProvider
import com.example.android_programming.vmodel.OrderViewModel
import com.example.android_programming.vmodel.SneakerViewModel

@Composable
fun RecyclerView(navHostController : NavHostController, orderViewModel: OrderViewModel, sneakerViewModel: SneakerViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        val list = sneakerViewModel.SneakerList.collectAsState(initial = emptyList()).value
        val numColumns = 2
        val chunkedList = list.chunked(numColumns)

        for (chunkedListItem in chunkedList) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                for (item in chunkedListItem) {
                    CardSneaker(item, navHostController, orderViewModel.selectedItems) { selectedItem ->
                        orderViewModel.addSelectedItem(selectedItem)
                    }
                }
            }
        }
    }
}


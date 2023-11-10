package com.example.android_programming.composeui.Screens.HomeScreen.SneakerRecyclerView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.android_programming.model.Sneaker
import com.example.android_programming.vmodel.AppViewModelProvider
import com.example.android_programming.vmodel.OrderViewModel
import com.example.android_programming.vmodel.SneakerViewModel

@Composable
fun RecyclerView(navHostController: NavHostController, orderViewModel: OrderViewModel, sneakerViewModel: SneakerViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        val sneakerLazyPagingItems = sneakerViewModel.sneakerList.collectAsLazyPagingItems()

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(
                count = sneakerLazyPagingItems.itemCount,
                key = sneakerLazyPagingItems.itemKey { sneaker -> sneaker.sneakerId!! }
            ) { index: Int ->
                val sneaker: Sneaker? = sneakerLazyPagingItems[index]
                if (sneaker != null) {
                    CardSneaker(sneaker, navHostController, orderViewModel.selectedItems) { selectedItem ->
                        orderViewModel.addSelectedItem(selectedItem)
                    }
                }
            }
        }
    }
}




package com.example.android_programming.composeui.Screens.HomeScreen.SneakerRecyclerView

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.android_programming.R
import com.example.android_programming.model.Sneaker
import com.example.android_programming.businessLogic.vmodel.AppViewModelProvider
import com.example.android_programming.businessLogic.vmodel.OrderViewModel
import com.example.android_programming.businessLogic.vmodel.SneakerViewModel
import com.example.android_programming.composeui.Screens.HomeScreen.FilterByBrand.FilterByBrand
import com.example.android_programming.composeui.Screens.HomeScreen.Sales
import com.example.android_programming.composeui.Screens.HomeScreen.SearchField.SearchField
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.builtins.serializer

@Composable
fun RecyclerView(navHostController: NavHostController, sneakerViewModel: SneakerViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {
        Row {
            // Поле для поиска
            SearchField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 20.dp, 20.dp, 10.dp)
                    .clip(RoundedCornerShape(10.dp))
            ) { searchText ->
                // Обработка введенного текста поиска
            }
        }
        Sales()
        FilterByBrand()
        val sneakerLazyPagingItems = sneakerViewModel.sneakerList.collectAsLazyPagingItems()
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(2)
        ) {
            items(
                count = sneakerLazyPagingItems.itemCount,
                key = sneakerLazyPagingItems.itemKey { sneaker -> sneaker.sneakerId!! }
            ) { index: Int ->
                val sneaker: Sneaker? = sneakerLazyPagingItems[index]
                if (sneaker != null) {
                    CardSneaker(sneaker, navHostController)
                }
            }
        }
    }
}





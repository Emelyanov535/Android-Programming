package com.example.android_programming.composeui.Screens.HomeScreen.SneakerRecyclerView

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.android_programming.SneakerViewModel
import com.example.android_programming.model.Sneaker
import kotlin.collections.chunked

@Composable
fun RecyclerView(navHostController : NavHostController, sneakerViewModel: SneakerViewModel = viewModel(factory = SneakerViewModel.factory)) {
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
                    CardSneaker(item, navHostController)
                }
            }
        }
    }
}

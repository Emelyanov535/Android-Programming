package com.example.android_programming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.android_programming.composeui.Header.Header
import com.example.android_programming.composeui.Navigation.Navigate
import com.example.android_programming.database.AppDatabase
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val context = LocalContext.current
    val sneakers = remember { mutableStateListOf<Sneaker>() }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            AppDatabase.getInstance(context).sneakerDao().getAllSneakers().collect { data ->
                sneakers.clear()
                sneakers.addAll(data)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Header()
        Navigate()
    }
}
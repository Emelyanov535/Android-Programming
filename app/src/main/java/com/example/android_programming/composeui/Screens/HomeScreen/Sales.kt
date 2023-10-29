package com.example.android_programming.composeui.Screens.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_programming.R

@Composable
@Preview
fun Sales() {
    Row(
        modifier = Modifier
            .padding(16.dp, 0.dp)
    ){
        Image(
            painter = painterResource(id = R.drawable.sales),
            contentDescription = "image",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
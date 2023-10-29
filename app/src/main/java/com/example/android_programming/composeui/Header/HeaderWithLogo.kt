package com.example.android_programming.composeui.Header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_programming.R

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(0.dp, 10.dp, 0.dp, 0.dp)
        )
    }
}


@Composable
@Preview
fun NavigatePreview(){
    Header()
}
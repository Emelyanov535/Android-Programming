package com.example.android_programming.composeui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.android_programming.R

@Composable
fun ErrorPlaceholder(message: String, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(value = 20F, type = TextUnitType.Sp),
            text = message,
            color = Color(0xFFFF1744)
        )
        Spacer(modifier = Modifier.padding(bottom = 10.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onBack() }
        ) {
            Text("Пися ПОПА")
        }
    }
}

@Composable
@Preview
fun dasdasd(){
    LoadingPlaceholder()
}

@Composable
fun LoadingPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = TextUnit(value = 25F, type = TextUnitType.Sp),
            text = "Пися ПОПА"
        )
    }
}
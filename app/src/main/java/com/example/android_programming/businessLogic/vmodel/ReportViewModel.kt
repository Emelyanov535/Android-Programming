package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.mutableStateOf

class ReportViewModel(): MyViewModel() {
    var dateFrom = mutableStateOf("")
    val dateTo = mutableStateOf("")
}
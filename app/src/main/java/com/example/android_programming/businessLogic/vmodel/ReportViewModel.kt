package com.example.android_programming.businessLogic.vmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.android_programming.api.model.ExpandedOrderRemote
import com.example.android_programming.api.model.OrderRemote
import com.example.android_programming.api.model.SneakerCountPair
import com.example.android_programming.businessLogic.repo.ReportRepository
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReportViewModel(private val reportRepository: ReportRepository): MyViewModel() {
    val dateFrom = mutableStateOf(0L)
    val dateTo = mutableStateOf(0L)

    private val _avvSum = MutableStateFlow(0.0)
    val avvSum: StateFlow<Double> = _avvSum


    val countOrder = mutableStateOf(0)
    var sneakerList = MutableLiveData<List<SneakerCountPair>>()
    var orderList = MutableLiveData<List<ExpandedOrderRemote>>()

    fun updateReportData(dateFrom: Long, dateTo: Long) {
        viewModelScope.launch {
            val report = reportRepository.getReportData(dateFrom, dateTo)
            countOrder.value = report.countOrder
            _avvSum.value = report.avvSum!!
            sneakerList.value = report.listPopularSneaker
            orderList.value = report.listOrder
        }
    }
}
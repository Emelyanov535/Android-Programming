package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.api.ApiStatus
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.nio.channels.ConnectionPendingException

open class MyViewModel : ViewModel() {
    var apiStatus by mutableStateOf(ApiStatus.DONE)
        private set

    var apiError by mutableStateOf("")
        private set

    fun runInScope(
        actionSuccess: suspend () -> Unit,
        actionError: suspend () -> Unit
    ) {
        viewModelScope.launch {
            apiStatus = ApiStatus.LOADING
            runCatching {
                actionSuccess()
                apiStatus = ApiStatus.DONE
                apiError = ""
            }.onFailure { e: Throwable ->
                when (e) {
                    is IOException,
                    is HttpException -> {
                        actionError()
                        apiStatus = ApiStatus.ERROR
                        apiError = e.localizedMessage ?: e.toString()
                    }
                    else -> throw e
                }
            }
        }
    }

    fun runInScope(actionSuccess: suspend () -> Unit) {
        runInScope(actionSuccess, actionError = {})
    }
}
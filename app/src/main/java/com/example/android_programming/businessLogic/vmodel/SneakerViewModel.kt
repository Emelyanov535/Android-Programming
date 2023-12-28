package com.example.android_programming.businessLogic.vmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.android_programming.R
import com.example.android_programming.api.repository.RestSneakerRepository
import com.example.android_programming.businessLogic.repo.SneakerRepository
import com.example.android_programming.model.Sneaker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class SneakerViewModel(private val sneakerRepository: SneakerRepository): MyViewModel() {
    var brand = mutableStateOf("")
    val model = mutableStateOf("")
    val description = mutableStateOf("")
    val price = mutableStateOf("")

    private val _sneakerList = MutableStateFlow<Flow<PagingData<Sneaker>>>(emptyFlow())
    val sneakerList: StateFlow<Flow<PagingData<Sneaker>>> get() = _sneakerList
    init {
        viewModelScope.launch {
            _sneakerList.value = sneakerRepository.getAllSneakers()
        }
    }
    fun insertSneaker(photo: Bitmap) = viewModelScope.launch {
        val sneaker = Sneaker(
            brand = brand.value,
            model = model.value,
            description = description.value,
            price = price.value.toDouble(),
            photo = photo
            )
        sneakerRepository.insertSneaker(sneaker)
    }

    fun deleteSneaker(sneaker :  Sneaker) = viewModelScope.launch {
        runInScope(
            actionSuccess = {
                sneakerRepository.deleteSneaker(sneaker)
                _sneakerList.value = sneakerRepository.getAllSneakers()
            }
        )

    }

    fun UpdateSneaker(sneaker: Sneaker) = viewModelScope.launch {
        sneakerRepository.updateSneaker(sneaker)
    }

    fun searchSneakersByFilter(filter: String) {
        viewModelScope.launch {
            val filteredSneakers = sneakerRepository.call(filter).cachedIn(viewModelScope)
            _sneakerList.value = filteredSneakers
        }
    }
    fun searchSneakersByAdidas() {
        viewModelScope.launch {
            val filteredSneakers = sneakerRepository.callAdidas().cachedIn(viewModelScope)
            _sneakerList.value = filteredSneakers
        }
    }
    fun searchSneakersByNike() {
        viewModelScope.launch {
            val filteredSneakers = sneakerRepository.callNike().cachedIn(viewModelScope)
            _sneakerList.value = filteredSneakers
        }
    }
}
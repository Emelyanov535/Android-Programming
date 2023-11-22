package com.example.android_programming.vmodel

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.android_programming.App

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            SneakerViewModel(app().container.sneakerRepo)
        }
        initializer {
            UserViewModel(app().container.userRepo)
        }
        initializer {
            OrderViewModel(app().container.orderRepo)
        }
        initializer {
            BasketViewModel(app().container.basketRepo)
        }
    }
}

fun CreationExtras.app(): App =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App)
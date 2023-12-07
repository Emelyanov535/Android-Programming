package com.example.android_programming.businessLogic.vmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android_programming.GlobalUser
import com.example.android_programming.R
import com.example.android_programming.api.model.UserRemoteSignIn
import com.example.android_programming.model.User
import com.example.android_programming.businessLogic.repo.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val userRepository: UserRepository): ViewModel() {

    var name = mutableStateOf("")
    val surname = mutableStateOf("")
    val email = mutableStateOf("")
    val password = mutableStateOf("")
    fun createUser() = viewModelScope.launch {
        val user = User(
            name = name.value,
            surname = surname.value,
            email = email.value,
            password = password.value,
            role = "USER",
            photo = R.drawable.shailushai
        )
        userRepository.createUser(user)
    }
    fun authUser() = viewModelScope.launch {
        val user = userRepository.authUser(UserRemoteSignIn(email.value, password.value))
        GlobalUser.getInstance().setUser(user)
    }

    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
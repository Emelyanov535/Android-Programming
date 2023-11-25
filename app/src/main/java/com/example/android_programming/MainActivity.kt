package com.example.android_programming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.android_programming.composeui.Header.Header
import com.example.android_programming.composeui.Navigation.Navigate
import com.example.android_programming.model.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        this.deleteDatabase("my-db")
        setContent {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header()

        Navigate()

    }
}

class GlobalUser private constructor() {
    private var user: User? = null

    fun setUser(user: User?) {
        this.user = user
    }

    fun getUser(): User? {
        return user
    }

    companion object {
        private var instance: GlobalUser? = null

        fun getInstance(): GlobalUser {
            return instance ?: synchronized(this) {
                instance ?: GlobalUser().also { instance = it }
            }
        }
    }
}
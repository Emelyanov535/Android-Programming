package com.example.android_programming.api

import com.example.android_programming.api.model.SneakerRemote
import com.example.android_programming.api.model.UserRemote
import com.example.android_programming.api.model.UserRemoteSignIn
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BackendService {

    //SNEAKER
    @GET("sneaker/get/{id}")
    suspend fun getSneaker(
        @Path("id") id: Int,
    ): SneakerRemote

    @GET("sneaker/getAll")
    suspend fun getSneakers(
        @Query("page") page: Int,
        @Query("size") size: Int,
    ): List<SneakerRemote>

    //USER
    @POST("user/signup")
    suspend fun SignUp(
        @Body user: UserRemote,
    ): UserRemote

    @POST("user/signin")
    suspend fun SignIn(
        @Body user: UserRemoteSignIn
    ): UserRemote

    companion object {
        private const val BASE_URL = "https://59k4pfj3-8080.euw.devtunnels.ms/api/"

        @Volatile
        private var INSTANCE: BackendService? = null

        fun getInstance(): BackendService {
            return INSTANCE ?: synchronized(this) {
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC
                val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .build()
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                    .build()
                    .create(BackendService::class.java)
                    .also { INSTANCE = it }
            }
        }
    }
}
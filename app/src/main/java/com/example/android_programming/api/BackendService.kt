package com.example.android_programming.api

import com.example.android_programming.api.model.BasketSneakerRemote
import com.example.android_programming.api.model.OrderRemote
import com.example.android_programming.api.model.OrderSneakerRemote
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
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @POST("sneaker/create")
    suspend fun createSneaker(
        @Body sneaker: SneakerRemote,
    ): SneakerRemote

    @PUT("sneaker/update/{id}")
    suspend fun updateSneaker(
        @Path("id") id: Int,
        @Body sneaker: SneakerRemote
    ): SneakerRemote

    @DELETE("sneaker/delete/{id}")
    suspend fun deleteSneaker(
        @Path("id") id: Int
    )

    @GET("sneaker/findSneakersByString/{string}")
    suspend fun findSneakersByString(
        @Path("string") str: String,
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

    //BASKET
    @POST("basket/createBasketSneaker")
    suspend fun createBasketSneaker(
        @Body basketSneaker: BasketSneakerRemote
    )

    @GET("basket/getUserBasketSneakers/{id}")
    suspend fun getUserBasketSneakers(
        @Path("id") id: Int
    ): List<SneakerRemote>

    @GET("basket/getUserBasket/{id}")
    suspend fun getUserBasket(
        @Path("id") id: Int
    ): Int

    @GET("basket/getQuantity/{basketId}/{sneakerId}")
    suspend fun getQuantity(
        @Path("basketId") basketId: Int,
        @Path("sneakerId") sneakerId: Int,
    ): Int

    @PUT("basket/incrementQuantity/{basketId}/{sneakerId}")
    suspend fun increment(
        @Path("basketId") basketId: Int,
        @Path("sneakerId") sneakerId: Int,
    )

    @PUT("basket/decrementQuantity/{basketId}/{sneakerId}")
    suspend fun decrement(
        @Path("basketId") basketId: Int,
        @Path("sneakerId") sneakerId: Int,
    )

    @GET("basket/getSneaker/{basketId}/{sneakerId}")
    suspend fun getSneaker(
        @Path("basketId") basketId: Int,
        @Path("sneakerId") sneakerId: Int,
    ): Boolean

    @GET("basket/removeSneaker/{basketId}/{sneakerId}")
    suspend fun deleteSneakerFromBasket(
        @Path("basketId") basketId: Int,
        @Path("sneakerId") sneakerId: Int,
    )

    @GET("basket/getUserPrice/{userId}")
    suspend fun getTotalPriceForUserBasket(
        @Path("userId") userId: Int
    ): Double

    @GET("basket/deleteAllSneakerFromBasket/{basketId}")
    suspend fun deleteAllSneakerFromBasket(
        @Path("basketId") basketId: Int
    )

    //ORDER
    @POST("order/createOrderSneaker")
    suspend fun createOrderSneaker(
        @Body orderSneaker: OrderSneakerRemote
    )

    @POST("order/create")
    suspend fun createOrder(
        @Body order: OrderRemote
    ): Long

    @GET("order/getUserOrders/{userId}")
    suspend fun getUserOrders(
        @Path("userId") userId: Int
    ) : List<OrderRemote>

    @GET("order/getSneakerFromOrder/{orderId}")
    suspend fun getSneakerFromOrder(
        @Path("orderId") orderId: Int
    ) : List<SneakerRemote>

    @GET("order/deleteOrder/{orderId}")
    suspend fun deleteOrder(
        @Path("orderId") orderId: Int
    )

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
package com.example.android_programming.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.android_programming.R
import com.example.android_programming.dao.BasketDao
import com.example.android_programming.dao.OrderDao
import com.example.android_programming.dao.SneakerDao
import com.example.android_programming.dao.UserDao
import com.example.android_programming.model.Basket
import com.example.android_programming.model.BasketSneakers
import com.example.android_programming.model.Order
import com.example.android_programming.model.OrderSneaker
import com.example.android_programming.model.RoleEnum
import com.example.android_programming.model.Sneaker
import com.example.android_programming.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Sneaker::class, User::class, Order::class, OrderSneaker::class, Basket::class, BasketSneakers::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sneakerDao(): SneakerDao
    abstract fun userDao(): UserDao
    abstract fun orderDao(): OrderDao
    abstract fun basketDao(): BasketDao

    companion object {
        private const val DB_NAME: String = "my-db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        suspend fun populateDatabase() {
            INSTANCE?.let { database ->
                // User
                val userDao = database.userDao()
                val user1 = User(null, "Artem", "Emelyanov", "artem@mail.ru", "123", RoleEnum.Admin)
                val user2 = User(null, "Danil", "Markov", "danil@mail.ru", "123", RoleEnum.User)
                val user3 = User(null, "Viktoria", "Presnyakova", "vika@mail.ru", "123", RoleEnum.User)
                userDao.createUser(user1)
                userDao.createUser(user2)
                userDao.createUser(user3)
                // Sneaker
                val sneakerDao = database.sneakerDao()
                val sneaker1 = Sneaker(null, "Nike", "Air Force 1", "nice", 159.99, R.drawable.img_1)
                val sneaker2 = Sneaker(null, "Adidas", "ZX 750", "beautiful", 169.99, R.drawable.img_2)
                val sneaker3 = Sneaker(null, "Reebok", "Classic", "amazing", 179.99, R.drawable.img_3)
                val sneaker4 = Sneaker(null, "Puma", "Classic", "normal", 189.99, R.drawable.img_4)
                sneakerDao.insert(sneaker1)
                sneakerDao.insert(sneaker2)
                sneakerDao.insert(sneaker3)
                sneakerDao.insert(sneaker4)
                // Order
                val basketDao = database.basketDao()
                val basket1 = Basket(1,1)
                val basket2 = Basket(2,2)
                val basket3 = Basket(3,3)
                basketDao.createBasket(basket1)
                basketDao.createBasket(basket2)
                basketDao.createBasket(basket3)
            }
        }

        fun getInstance(appContext: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    appContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            CoroutineScope(Dispatchers.IO).launch {
                                populateDatabase()
                            }
                        }
                    })
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
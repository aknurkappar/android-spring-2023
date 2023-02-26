package com.example.roomdatabse2.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.example.roomdatabse2.utils.subscribeOnBackground

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao() : UserDao /*data access object*/

    companion object {
        private var INSTANCE: UserDatabase? = null
        /*singleton class – only one instance of its class*/

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user-database"
                    ).build()
                }
                return instance
            }
        }

//        private val roomCallBack = object : Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//                super.onCreate(db)
//                populateDatabase(instance!!)
//            }
//        }
//
//        fun populateDatabase(db: UserDatabase) {
//            val userDao = db.UserDao()
//            val users: LiveData<List<User>> = userDao.readAllUsers()
//            subscribeOnBackground {
//                userDao.addUser(User("Aknur", "a_kapparova@kbtu.kz", "123", 19, 1, 1))
//                userDao.addUser(User("Aynur", "aralbaikyzy040203@gmail.com", "456", 21, 2, 2))
//            }
//        }
    }
}
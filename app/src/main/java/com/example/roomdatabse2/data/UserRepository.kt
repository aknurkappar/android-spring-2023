package com.example.roomdatabse2.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao :  UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readAllUsers()

    suspend fun deleteAllUser(){
        userDao.deleteAllUser()
    }

    suspend fun addUser(user : User){ /*we use co-routines in a view model*/
        userDao.addUser(user)
    }

    suspend fun deleteUser(user : User){ /*we use co-routines in a view model*/
        userDao.deleteUser(user)
    }


}
package com.example.roomdatabse2.data


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Ignore
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao{

    @Query("select * from user_table order by id asc")
    fun readAllUsers() : LiveData<List<User>>

    @Query("delete from user_table")
    suspend fun deleteAllUser()

    @Insert /*(onConflict = OnConflictStrategy.IGNORE)*/
    suspend fun addUser(user : User) /*suspend*/

    @Update
    suspend fun updateUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)

}
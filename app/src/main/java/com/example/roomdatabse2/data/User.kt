package com.example.roomdatabse2.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    val name: String,
    val email: String,
    val password: String,
    @PrimaryKey(autoGenerate = false) val id: Int? = null)

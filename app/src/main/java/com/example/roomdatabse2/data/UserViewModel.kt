package com.example.roomdatabse2.data

import android.app.Application
import android.service.autofill.UserData
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//communication bwn Rep and UI // data changes -> to UI
class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val myDatabase = UserDatabase.getInstance(application)

    override fun onCleared() {
        super.onCleared()
        try {
            myDatabase.close()
        } catch (e: Exception) {
            // handle any exceptions here
        }
    }

    val readAllData: LiveData<List<User>>
    private val repository: UserRepository


    init {
        val userDao = UserDatabase.getInstance(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun deleteAllUser(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUser()
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    class UserViewModelFactory(
        private val application: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                return UserViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
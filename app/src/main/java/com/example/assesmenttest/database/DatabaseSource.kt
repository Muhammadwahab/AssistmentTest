package com.example.assesmenttest.database

import androidx.lifecycle.LiveData
import com.example.assesmenttest.models.User
import javax.inject.Inject

class DatabaseSource @Inject constructor(private val userDao: UserDao) {
    suspend fun addUser(user: User): Long {
        return userDao.insert(user)
    }

    suspend fun getUser(): User? {
        return userDao.getUser()
    }

      fun getLiveDataUser(): LiveData<User?>? {
        return userDao.getLiveDataUser()
    }

    suspend fun deleteUser() {
        return userDao.delete()
    }
}
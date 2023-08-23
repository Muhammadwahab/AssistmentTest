package com.example.assesmenttest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assesmenttest.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM User limit 1")
    suspend fun getUser(): User?


    @Query("SELECT * FROM User limit 1")
     fun getLiveDataUser(): LiveData<User?>?

    @Query("SELECT * FROM User limit 1")
    fun getUserToken(): User?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User): Long


    @Query("DELETE FROM User")
    suspend fun delete()

}
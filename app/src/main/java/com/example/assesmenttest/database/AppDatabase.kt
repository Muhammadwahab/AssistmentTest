package com.example.assesmenttest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assesmenttest.models.User

@Database(entities = [ User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}


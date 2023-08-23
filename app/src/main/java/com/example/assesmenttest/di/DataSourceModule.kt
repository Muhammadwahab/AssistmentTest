package com.example.assesmenttest.di

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.assesmenttest.Constants
import com.example.assesmenttest.GlobalData
import com.example.assesmenttest.database.AppDatabase
import com.example.assesmenttest.database.DatabaseSource
import com.example.assesmenttest.database.UserDao
import com.example.assesmenttest.network.RemoteDataSource
import com.example.assesmenttest.network.WebService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(
        webService: WebService
    ): RemoteDataSource {
        Log.e(DataSourceModule::class.simpleName,"provideRemoteDataSource")

        return RemoteDataSource(webService)
    }


    @Provides
    @Singleton
    fun providerDatabase(
        @ApplicationContext appContext: Context
    ): AppDatabase {
        Log.e(DataSourceModule::class.simpleName,"providerDatabase")

        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, Constants.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providerDatabaseSource(
        appDatabase: UserDao
    ): DatabaseSource {
        Log.e(DataSourceModule::class.simpleName,"providerDatabaseSource")

        return DatabaseSource(appDatabase)
    }

    @Provides
    fun providerUserDao(
        appDatabase: AppDatabase
    ): UserDao {
        Log.e(DataSourceModule::class.simpleName,"providerUserDao")

        return appDatabase.userDao()
    }


    @Provides
    fun providerMoshi(): Moshi {
        Log.e(DataSourceModule::class.simpleName,"providerMoshi")

        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun globalData(): GlobalData {
        Log.e(DataSourceModule::class.simpleName,"GlobalData")

        return GlobalData()
    }

}
package com.example.assesmenttest.di

import android.content.Context
import com.example.assesmenttest.Constants.TIMEOUT
import com.example.assesmenttest.network.WebService
import com.example.assesmenttest.network.interceptors.APIInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
  }

  @Provides
  @Singleton
  fun provideApiInterceptor(): APIInterceptor {
    return APIInterceptor()
  }

  @Provides
  @Singleton
  fun provideOkhttpClient(httpLoggingInterceptor:HttpLoggingInterceptor,apiInterceptor: APIInterceptor):  OkHttpClient.Builder {
    val okHttpBuilder =
      OkHttpClient.Builder().connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)

    okHttpBuilder.addInterceptor(httpLoggingInterceptor)
    okHttpBuilder.addInterceptor(apiInterceptor)
    return okHttpBuilder
  }


  @Provides
  @Singleton
  fun provideWebService(@ApplicationContext appContext: Context,builder: OkHttpClient.Builder):  WebService {
    val retrofit = Retrofit.Builder().baseUrl("https://pixabay.com/")
      .client(builder.build())
      .addConverterFactory(GsonConverterFactory.create(Gson())).build()
    return retrofit.create(WebService::class.java)
  }
}
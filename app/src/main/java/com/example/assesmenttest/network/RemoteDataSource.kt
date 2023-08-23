package com.example.assesmenttest.network

import android.util.Log
import com.example.assesmenttest.network.response.PixaBayResponse
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val webService: WebService, private val coroutineScope: CoroutineScope = CoroutineScope(
        Dispatchers.IO
    )
) {


    private val _unAuthorized = Channel<Boolean>()
    internal val unAuthorized = _unAuthorized.receiveAsFlow()



    suspend fun getPixaBayApi(queryParameters: Map<String, String>): Result<*> {
        val data=withContext(coroutineScope.coroutineContext) {
            callDifferentApi {
                webService.getPixaBay(queryParameters)
            }
        }
        return if (data.data is PixaBayResponse){
            Result.Success(data.data)
        } else {
            Result.Error<String>("some thing went wrong")
        }

    }

    private suspend fun <T> callApi(api: suspend () -> Response<T>): Result<ResponseGeneral<*>?> {
        return try {
            val apiResponse = api.invoke() as Response<ResponseGeneral<T>>
            if (apiResponse.isSuccessful) {
                val responseBody = apiResponse.body()

                Result.Success(responseBody)
            } else {
                if (apiResponse.code() == 401) {
                    coroutineScope.launch(Dispatchers.IO) {
                        _unAuthorized.send(true)
                    }
                }

                val response = handleServerError(
                    apiResponse.errorBody()?.string().toString(), java.lang.Exception()
                )

                Result.Error(
                    "error", response
                )
            }
        } catch (e: UnknownHostException) {
            Log.e(RemoteDataSource::class.simpleName,"exception is "+e.message)
            Result.Error("error", handleServerError("some thing went wrong with network", e))
        } catch (e: Exception) {
            Log.e(RemoteDataSource::class.simpleName,"exception is "+e.message)

            Result.Error("error", handleServerError("some thing went wrong", e))
        }
    }



    private suspend fun <T> callDifferentApi(api: suspend () -> Response<T>): Result<*> {
        return try {
            val apiResponse = api.invoke() as Response<T>
            if (apiResponse.isSuccessful) {

                val responseBody = apiResponse.body()

                Result.Success(responseBody)
            } else {
                if (apiResponse.code() == 401) {
                    coroutineScope.launch(Dispatchers.IO) {
                        _unAuthorized.send(true)
                    }
                }

                val response = handleServerError(
                    apiResponse.errorBody()?.string().toString(), java.lang.Exception()
                )

                Result.Error(
                    "error", "Some thing went wrong"
                )
            }
        } catch (e: UnknownHostException) {
            Log.e(RemoteDataSource::class.simpleName,"exception is "+e.message)
            Result.Error("error", handleServerError("some thing went wrong with network", e))
        } catch (e: Exception) {
            Log.e(RemoteDataSource::class.simpleName,"exception is "+e.message)

            Result.Error("error", handleServerError("some thing went wrong", e))
        }
    }


    @OptIn(ExperimentalStdlibApi::class)
    fun handleServerError(error: String, e: Exception): ResponseGeneral<*>? {
        return try {
            if (error.isNotEmpty()) {
                val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                val adapter: JsonAdapter<ResponseGeneral<Any>> = moshi.adapter()
                return adapter.fromJson(error)
            } else {
                val response = ResponseGeneral(null, null)
                response
            }
        } catch (exception: Exception) {
            if (e is UnknownHostException) {
                Log.e(RemoteDataSource::class.java.simpleName, "exception is " + exception.message)
                val response = ResponseGeneral(
                    null, null )
                response
            } else {
                Log.e(RemoteDataSource::class.java.simpleName, "exception is " + exception.message)
                val response = ResponseGeneral(
                    null, null )
                response
            }

        }

    }

}
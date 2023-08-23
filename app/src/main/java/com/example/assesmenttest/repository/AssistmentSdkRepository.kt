package com.example.assesmenttest.repository

import com.example.assesmenttest.GlobalData
import com.example.assesmenttest.network.RemoteDataSource
import com.example.assesmenttest.database.DatabaseSource
import com.example.assesmenttest.database.datastore.ProtoDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AssistmentSdkRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataSource: DatabaseSource,
    private val protoDataSource: ProtoDataSource,
    private val globalData: GlobalData?,
) {


    fun getRemoteDataSource(): RemoteDataSource {
        return remoteDataSource
    }


    fun getGlobalData(): GlobalData? {
        return globalData
    }


}

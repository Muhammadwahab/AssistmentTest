package com.example.assesmenttest.database.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import com.codelab.android.datastore.DataStorePreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import java.io.IOException
import javax.inject.Inject

class ProtoDataSource @Inject constructor(private val dataStorePreferences: DataStore<DataStorePreferences>) {

    private val TAG: String = ProtoDataSource::class.java.simpleName

    val dataStorePreferencesFlow: Flow<DataStorePreferences> = dataStorePreferences.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(DataStorePreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }


   suspend fun updateAppVersion(){
        dataStorePreferences.updateData {
            it.toBuilder().setAppVersion(true).build()
        }
    }

    suspend fun getAppVersion(): DataStorePreferences {
        return dataStorePreferences.data.first()
    }

    suspend fun getAppFirstRun(): Boolean {
        return dataStorePreferences.data.first().isFirstRun
    }

    suspend fun setAppFirstRun(value : Boolean) {
            dataStorePreferences.updateData {
                it.toBuilder().setIsFirstRun(value).build()
            }
    }

}
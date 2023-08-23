package com.example.assesmenttest.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.assesmenttest.pagingsource.PixaPagingSource
import com.example.assesmenttest.repository.AssistmentSdkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: AssistmentSdkRepository): ViewModel() {


    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        PixaPagingSource(repository)
    }.flow.cachedIn(viewModelScope)
}
package com.example.assesmenttest.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.assesmenttest.network.response.Hit
import com.example.assesmenttest.network.response.PixaBayResponse
import com.example.assesmenttest.repository.AssistmentSdkRepository
import com.example.assesmenttest.states.PixaBayState

class PixaPagingSource(
    private val repository: AssistmentSdkRepository
) : PagingSource<Int, PixaBayState>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, PixaBayState> {

        // Start refresh at page 1 if undefined.
        val currentPageNumber = params.key ?: 1

        val queryParams = mutableMapOf<String, String>()
        queryParams["key"] = "5303976-fd6581ad4ac165d1b75cc15b3"
        queryParams["q"] = "kitten"
        queryParams["image_type"] = "photo"
        queryParams["page"] = currentPageNumber.toString()
        return when (val response =
            repository.getRemoteDataSource().getPixaBayApi(queryParams)) {
            is com.example.assesmenttest.network.Result.Success -> {

                val responseData = response.data as PixaBayResponse
                val nextPage = currentPageNumber + 1

                LoadResult.Page<Int,PixaBayState>( data = pixaBayTransformation(responseData.hits), prevKey = null, nextKey = nextPage)


            }
            else -> {
                LoadResult.Error(Throwable("Some thing went wrong"))
            }
        }
    }

    private fun pixaBayTransformation(hits: List<Hit?>?): List<PixaBayState> {

        val stateList=hits?.map {
            PixaBayState(it?.id,it?.largeImageURL,it?.tags,it?.user,it?.likes.toString())
        }
        return stateList!!
    }

    override fun getRefreshKey(state: PagingState<Int, PixaBayState>): Int? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
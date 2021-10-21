package com.team.data.dataSource.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.team.data.remote.IRemoteData
import com.team.entities.weather.remote.request.WeatherRequest
import com.team.entities.weather.remote.response.ListItem
import com.team.entities.weather.remote.response.WeatherResponse
import java.lang.Exception
import javax.inject.Inject

class PagingDataSource @Inject constructor(private val iRemoteData: IRemoteData) :
    PagingSource<Int, ListItem>() {

    override fun getRefreshKey(state: PagingState<Int, ListItem>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ListItem> {
        val page = params.key ?: 1
        return LoadResult.Error(Exception())
    /*try {
         val data=iRemoteData.getWeather(page.toString(),WeatherRequest().apiKey)
            LoadResult.Page(
                data = WeatherResponse().list!!,
                prevKey = if (page==1) null else page-1,
                nextKey = if (WeatherResponse().list!!.isEmpty()) null else page+1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }*/
    }
}
package com.team.data.dataSource.remote

import com.team.data.remote.IRemoteData
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val iRemoteData: IRemoteData) : IRemoteDataSource {


  /*  override fun getData(key: String): Single<CurrenciesResponse> {

        return iRemoteData.getData(key)
    }*/
}
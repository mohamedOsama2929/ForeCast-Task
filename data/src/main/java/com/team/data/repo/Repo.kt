package com.team.data.repo

import com.team.data.dataSource.local.ILocalDataSource
import com.team.data.dataSource.remote.IRemoteDataSource
import com.team.domain.domainRepo.IDomainRepo
import javax.inject.Inject

class Repo @Inject constructor(
    val iRemoteDataSource: IRemoteDataSource,
    val iLocalDataSource: ILocalDataSource
) : IDomainRepo {

    /*----------------------------------------Remote----------------------------------------*/

 /*   override fun getData(key: String): Single<CurrenciesResponse> {
        return iRemoteDataSource.getData(key)

    }*/

    /*----------------------------------------Local----------------------------------------*/
/*
    override fun saveData(currencies: Currencies) {
        iLocalDataSource.saveData(currencies)
    }*/
}
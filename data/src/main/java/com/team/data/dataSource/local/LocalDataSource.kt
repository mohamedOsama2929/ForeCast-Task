package com.team.data.dataSource.local

import com.team.data.local.MainDao
import javax.inject.Inject

class LocalDataSource @Inject constructor(val mainDao: MainDao) : ILocalDataSource {


   /* @Inject
    lateinit var currenciesCacheMapper: CurrenciesCacheMapper

    override fun saveData(currencies: Currencies) {
        return mainDao.insertAll(currenciesCacheMapper.mapToEntity(currencies))
    }*/
}
package com.team.domain.use_case.weather.local

import com.team.domain.domainRepo.IDomainRepo
import com.team.domain.excuter.PostExecutionThread
import com.team.domain.excuter.ThreadExecutor
import com.team.domain.utils.SingleUseCase
import com.team.entities.weather.local.WeatherLocal
import io.reactivex.Single
import javax.inject.Inject

class GetCachedWeatherUseCase @Inject constructor(
    private val repo: IDomainRepo,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<WeatherLocal, String>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: String?): Single<WeatherLocal> {

        return repo.getCachedWeather(params.toString())
    }
}
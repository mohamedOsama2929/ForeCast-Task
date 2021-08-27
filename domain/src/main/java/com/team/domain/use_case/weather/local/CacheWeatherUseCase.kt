package com.team.domain.use_case.weather.local

import com.team.domain.domainRepo.IDomainRepo
import com.team.domain.excuter.PostExecutionThread
import com.team.domain.excuter.ThreadExecutor
import com.team.domain.utils.CompletableUseCase
import com.team.entities.weather.models.Weather
import io.reactivex.Completable
import javax.inject.Inject

class CacheWeatherUseCase
@Inject constructor(
    private val repo: IDomainRepo,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<Weather>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: Weather?): Completable {
        return Completable.fromCallable {
            if (params != null) {
                repo.cacheWeather(weather =params)
            }
        }
    }
}
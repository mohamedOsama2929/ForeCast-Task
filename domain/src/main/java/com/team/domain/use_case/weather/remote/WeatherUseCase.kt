package com.team.domain.use_case.weather.remote

import com.team.domain.domainRepo.IDomainRepo
import com.team.domain.excuter.PostExecutionThread
import com.team.domain.excuter.ThreadExecutor
import com.team.domain.utils.SingleUseCase
import com.team.entities.weather.remote.request.WeatherRequest
import com.team.entities.weather.remote.response.WeatherResponse
import io.reactivex.Single
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repo: IDomainRepo,
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<WeatherResponse, WeatherRequest>(threadExecutor, postExecutionThread) {
    override fun buildUseCaseObservable(params: WeatherRequest?): Single<WeatherResponse> {

        return repo.getWeather(city = params?.city.toString(), apiKey = params?.apiKey.toString())
    }
}
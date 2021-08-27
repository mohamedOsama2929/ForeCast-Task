package com.team.mvvmstructure.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.team.core.utils.NetworkState
import com.team.core.utils.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val disposables: CompositeDisposable = CompositeDisposable()
    val errorState: SingleLiveEvent<String> = SingleLiveEvent()
    val networkState: SingleLiveEvent<NetworkState> = SingleLiveEvent()


    fun addToDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    @JvmName("getNetworkState1")
    fun getNetworkState(): SingleLiveEvent<NetworkState> = networkState


    fun getError(): LiveData<String> = errorState


}
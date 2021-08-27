package com.team.mvvmstructure.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApp : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: BaseApp? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}
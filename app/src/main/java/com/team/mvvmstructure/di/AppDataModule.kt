package com.team.mvvmstructure.di

import com.team.data.local.MainDao
import com.team.data.local.MainDatabase
import com.team.data.remote.IRemoteData
import com.team.data.repo.Repo
import com.team.domain.domainRepo.IDomainRepo
import com.team.domain.excuter.PostExecutionThread
import com.team.domain.excuter.ThreadExecutor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppDataModule {

    @Provides
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor {
        return jobExecutor
    }

    @Provides
    fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    fun provideRepo(repo: Repo): IDomainRepo {
        return repo
    }

    @Singleton
    @Provides
    fun provideRetrofit(retrofit: Retrofit): IRemoteData {
        return retrofit.create(IRemoteData::class.java)
    }

    @Singleton
    @Provides
    fun provideMainDAO(blogDatabase: MainDatabase): MainDao {
        return blogDatabase.mainDao()
    }

}
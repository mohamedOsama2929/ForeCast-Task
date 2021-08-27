package com.team.core.di.local

import android.content.Context
import androidx.room.Room
import com.team.data.dataSource.local.ILocalDataSource
import com.team.data.dataSource.local.LocalDataSource
import com.team.data.local.MainDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

//    @Singleton
//    @Provides
//    fun provideCacheMapper(): EntityMapper<BlogCacheEntity, Blog>{
//        return CacheMapper()
//    }

    @Singleton
    @Provides
    fun provideMainDb(@ApplicationContext context: Context): MainDatabase {
        return Room
            .databaseBuilder(
                context,
                MainDatabase::class.java,
                MainDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideILocalDataSource(localDataSource: LocalDataSource): ILocalDataSource {
        return localDataSource
    }

//    @Singleton
//    @Provides
//    fun provideBlogDaoService(
//        mainDao: MainDao
//    ):BlogDaoService{
//        return BlogDaoServiceImpl(blogDao)
//    }
//
//    @Singleton
//    @Provides
//    fun provideCacheDataSource(
//        blogDaoService: BlogDaoService,
//        cacheMapper: CacheMapper
//    ): CacheDataSource{
//        return CacheDataSourceImpl(blogDaoService, cacheMapper)
//    }
}


























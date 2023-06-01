package com.example.cytrack.di

import android.app.Application
import android.content.Context
import com.example.cytrack.data.remote.service.PandaScoreApiService
import com.example.cytrack.data.remote.service.PandaScoreService
import com.example.cytrack.data.repository.GameRepositoryImpl
import com.example.cytrack.domain.GameRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application.applicationContext

    @Provides
    @Singleton
    fun providePandaScoreApiService(): PandaScoreApiService {
        return PandaScoreService.instance
    }

    @Provides
    @Singleton
    fun provideGameRepository(
        remoteSource: PandaScoreApiService,
    ): GameRepository {
        return GameRepositoryImpl(
            remoteSource
        )
    }
}

package com.ygoular.commitreader.di.module

import com.ygoular.commitreader.data.remote.api.GitHubService
import com.ygoular.commitreader.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RetrofitModule {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
    }

    @ApplicationScope
    @Provides
    fun provideApiInterface(retrofit: Retrofit): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }

    @ApplicationScope
    @Provides
    fun provideService(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package com.example.ejercicio.core.di

import com.example.ejercicio.core.data.remote.MovieRepositoryImp
import com.example.ejercicio.core.data.remote.dto.MovieApi
import com.example.ejercicio.core.data.remote.interceptor.ApiKeyInterceptor
import com.example.ejercicio.core.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CoreModule {
    @Provides
    @Singleton
    fun providerApi() : MovieApi{
        val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()
        return Retrofit.Builder().baseUrl(MovieApi.BASE_URL).
                addConverterFactory(MoshiConverterFactory.create()).
                client(client).build().create()
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: MovieApi
    ): MovieRepository{
        return MovieRepositoryImp(api)
    }
}
package com.example.ejercicio.core.di

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import com.example.ejercicio.core.data.local.MovieDao
import com.example.ejercicio.core.data.local.MovieDatabase
import com.example.ejercicio.core.data.remote.MovieRepositoryImp
import com.example.ejercicio.core.data.remote.dto.MovieApi
import com.example.ejercicio.core.data.remote.interceptor.ApiKeyInterceptor
import com.example.ejercicio.core.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
        return Retrofit.Builder().baseUrl(MovieApi.BASE_URL).
                addConverterFactory(MoshiConverterFactory.create()).
                client(client).build().create()
    }

    @Singleton
    @Provides
    fun provideDatabase(application: Application): MovieDatabase{
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movies_db").build()
    }

    @Singleton
    @Provides
    fun provideDatabaseDao(database: MovieDatabase): MovieDao{
        return database.dao
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: MovieApi,
        dao: MovieDao
    ): MovieRepository{
        return MovieRepositoryImp(api, dao)
    }
}
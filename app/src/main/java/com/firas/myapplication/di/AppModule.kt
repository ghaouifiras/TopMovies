package com.firas.myapplication.di

import com.firas.myapplication.data.Api.ApiService
import com.firas.myapplication.data.repository.MovieRepositoryImpl
import com.firas.myapplication.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private const val API_KEY =
        ""

    @Provides
    @Singleton
    fun provideApi(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val apiKeyInterceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url
            val newUrl = originalUrl.newBuilder()
                .addQueryParameter("api_key", API_KEY)
                .build()
            val newRequest = originalRequest.newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }

        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMealRepository(api: ApiService): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}

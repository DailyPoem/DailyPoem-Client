package com.patrick.dailypoem.di

import com.google.gson.GsonBuilder
import com.patrick.dailypoem.data.network.PoemService
import com.patrick.dailypoem.data.network.RandomImageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    private const val BASE_URL = "http://34.64.233.183/"
    private const val BASE_RANDOM_URL = "https://api.unsplash.com/"

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    @Named("poem")
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    @Named("random")
    fun provideRandomRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_RANDOM_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()

    @Singleton
    @Provides
    fun providePoemService(@Named("poem") retrofit: Retrofit): PoemService =
        retrofit.create(PoemService::class.java)

    @Singleton
    @Provides
    fun provideRandomImageService(@Named("random") retrofit: Retrofit): RandomImageService =
        retrofit.create(RandomImageService::class.java)
}
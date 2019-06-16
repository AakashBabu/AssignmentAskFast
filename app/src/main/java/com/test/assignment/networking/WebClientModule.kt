package com.test.assignment.networking

import com.test.gambit.utils.UserAgentInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class WebClientModule {

    @Provides
    fun provideClient():OkHttpClient{
        val client = OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor(UserAgentInterceptor())
            .build()
        return client;
    }

    @Provides
    fun provideRetrofit(client:OkHttpClient):Retrofit{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/").client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit;
    }

    @Provides
    fun provideAPIService(retrofit:Retrofit): WebClientService {
        return WebClientService(retrofit)
    }
}
package com.sina.cinamovie.di

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Singleton
//    @Provides
//    fun provideOkHttpClient(
//        tokenRepo: TokenRepo?,
//        sharedPreferences: SharedPreferences?
//    ): OkHttpClient? {
//        val okHttpClient = Builder()
//        return okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(60, TimeUnit.SECONDS)
//            .authenticator(TokenAuthenticator(tokenRepo))
//            .addInterceptor(TokenInterceptor(sharedPreferences))
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideApiService(retrofit: Retrofit): ApiService? {
//        return retrofit.create(ApiService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideRetrofit(okHttpClient: OkHttpClient?, tools: Tools): Retrofit? {
//        val gson: Gson = GsonBuilder().setLenient().create()
//        return Builder()
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .baseUrl(tools.getBaseUrl())
//            .client(okHttpClient)
//            .build()
//    }

}
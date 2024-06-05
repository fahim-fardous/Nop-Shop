package com.example.nopshop.di

import android.content.Context
import android.content.SharedPreferences
import com.example.nopshop.db.AppDatabase
import com.example.nopshop.network.ApiClient
import com.example.nopshop.network.api.AuthenticationApi
import com.example.nopshop.network.api.HomeApi
import com.example.nopshop.network.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase(context)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return ApiClient.getRetrofit()
    }

    @Provides
    @Singleton
    fun provideAuthenticationApi(retrofit: Retrofit):AuthenticationApi{
        return retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit):HomeApi{
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit):ProductApi{
        return retrofit.create(ProductApi::class.java)
    }
}
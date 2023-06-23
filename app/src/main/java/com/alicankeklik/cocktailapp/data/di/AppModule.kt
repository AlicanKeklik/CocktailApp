package com.alicankeklik.cocktailapp.data.di

import com.alicankeklik.cocktailapp.data.remote.dto.CocktailAppApi
import com.alicankeklik.cocktailapp.data.repository.CocktailRepositoryImpl
import com.alicankeklik.cocktailapp.domain.repository.CocktailRepository
import com.alicankeklik.cocktailapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCocktailApi():CocktailAppApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CocktailAppApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCocktailRepository(api: CocktailAppApi):CocktailRepository{
        return CocktailRepositoryImpl(api)
    }
}
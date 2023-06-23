package com.alicankeklik.cocktailapp.data.remote.dto

import com.alicankeklik.cocktailapp.domain.model.CocktailDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailAppApi {
@GET("api/json/v1/1/search.php")
suspend fun getCocktail(
    @Query("s") searchString: String
) : CocktailListDto

@GET("api/json/v1/1/lookup.php")
suspend fun getCocktailDetail(
    @Query("i") cocktailId:String
) : CocktailListDto

}

//www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita
//www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
package com.alicankeklik.cocktailapp.domain.use_case.getcocktaildetail

import com.alicankeklik.cocktailapp.data.remote.dto.toCocktailDetail
import com.alicankeklik.cocktailapp.domain.model.CocktailDetail
import com.alicankeklik.cocktailapp.domain.repository.CocktailRepository
import com.alicankeklik.cocktailapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOError
import javax.inject.Inject

class GetCocktailDetailUseCase @Inject constructor(private val repository:CocktailRepository){
    fun executeGetCocktailDetail(cocktailId:String):Flow<Resource<CocktailDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val cocktailDetail = repository.getCocktailDetail(cocktailId)
                emit(Resource.Success(cocktailDetail.toCocktailDetail().toCocktailDetail()))
            }catch (e: IOError){
                emit(Resource.Error("No Internet connection"))
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage?: "Error"))
            }

        }
    }
}
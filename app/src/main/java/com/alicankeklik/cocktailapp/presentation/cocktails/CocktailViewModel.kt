package com.alicankeklik.cocktailapp.presentation.cocktails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alicankeklik.cocktailapp.domain.use_case.getcocktail.GetCocktailUseCase
import com.alicankeklik.cocktailapp.presentation.cocktails.CocktailListState
import com.alicankeklik.cocktailapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
@HiltViewModel()
class CocktailViewModel @Inject constructor(
    private val getCoinUseCase: GetCocktailUseCase
) :ViewModel() {

    private val _state = mutableStateOf(CocktailListState())
    val state:State<CocktailListState> = _state
    private var job : Job? = null
    init {
        getCocktails(_state.value.search)
    }
    private fun getCocktails(search :String ){
        job?.cancel()
      job =  getCoinUseCase.executeGetCocktails(search).onEach {
         result ->
           when(result){
               is Resource.Success ->{
                   _state.value = CocktailListState(cocktails = result.data?.toList()?: emptyList())

               }
               is Resource.Loading->{
                   _state.value = CocktailListState(isLoading = true)
               }
               is Resource.Error->{
                  _state.value = CocktailListState(error = result.message?:"An uxexpected error occured")

               }
           }
       }.launchIn(viewModelScope)
    }
    fun onEvent(event: CocktailEvent){
        when(event){
            is CocktailEvent.Search -> {
                getCocktails(event.searchString)
            }
        }
    }
}
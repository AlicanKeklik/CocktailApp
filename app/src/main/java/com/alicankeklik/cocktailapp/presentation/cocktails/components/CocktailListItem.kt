package com.alicankeklik.cocktailapp.presentation.cocktails.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.alicankeklik.cocktailapp.domain.model.Cocktail

@Composable
fun CocktailListItem(
    cocktail: Cocktail,
    onItemClick :(Cocktail)->Unit
){
 Row(modifier = Modifier
     .fillMaxWidth()
     .clickable { onItemClick(cocktail) }
     .padding(20.dp),
 horizontalArrangement =Arrangement.SpaceBetween) {
     AsyncImage(
         model = cocktail.strDrinkThumb,
         contentDescription = cocktail.strDrink,
         modifier = Modifier.width(200.dp)
             .height(200.dp))
     Spacer(modifier = Modifier.width(20.dp))
     Column(modifier = Modifier.align(Alignment.CenterVertically), horizontalAlignment = Alignment.CenterHorizontally) {
     Text(text = "${cocktail.strDrink}" ,
         color = Color.White)
     }
 }
}
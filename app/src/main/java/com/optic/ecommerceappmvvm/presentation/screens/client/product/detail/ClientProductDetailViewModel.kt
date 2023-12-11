package com.optic.ecommerceappmvvm.presentation.screens.client.product.detail

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.optic.ecommerceappmvvm.domain.model.Product
import com.optic.ecommerceappmvvm.domain.model.ShoppingBagProduct
import com.optic.ecommerceappmvvm.domain.useCase.shopping_bag.ShoppingBagUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val shoppingBagUseCase: ShoppingBagUseCase,
    @ApplicationContext val context: Context
) : ViewModel() {

    var data = savedStateHandle.get<String>("product")
    var product = Product.fromJson(data!!)
    var productImages = listOf<String>(product.image1 ?: "", product.image2 ?: "")

    var quantity by mutableStateOf(0)
        private set

    var price by mutableStateOf(0.0)
        private set

    init {
        getShoppingBagProduct()
    }

    fun add()  {
        quantity = quantity + 1
        price = product.price * quantity
    }

    fun remove()  {
        if (quantity > 0) {
            quantity = quantity - 1
            price = product.price * quantity
        }
    }

    fun getShoppingBagProduct() = viewModelScope.launch {

        val result = shoppingBagUseCase.findById(product.id ?: "")
        quantity = result?.quantity ?: 0
        price = product.price * quantity

    }

    fun saveItem() = viewModelScope.launch {
        Log.d("ClientProductDetailViewModel", "Id: ${product.id}")
        if (quantity > 0) {
            val shoppingBagProduct = ShoppingBagProduct(
                id = product.id ?: "",
                name = product.name,
                price = product.price,
                image1 = product.image1 ?: "",
                idCategory = product.idCategory,
                quantity = quantity
            )
            shoppingBagUseCase.add(shoppingBagProduct)
            Toast.makeText(context, "producto agregado correctamente", Toast.LENGTH_LONG).show()
        }
    }

}
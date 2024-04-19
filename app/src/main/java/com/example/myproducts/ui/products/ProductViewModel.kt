package com.example.myproducts.ui.products

import androidx.lifecycle.ViewModel
import com.example.myproducts.models.Product
import com.example.myproducts.network.NetworkResponse
import com.example.myproducts.network.models.NetworkResourceError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductViewModel(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val mProductList =
        MutableStateFlow<NetworkResponse<out Response<List<Product>>, out NetworkResourceError>?>(
            null
        )

    val productList: StateFlow<NetworkResponse<out Response<List<Product>>, out NetworkResourceError>?> =
        mProductList

    /**
     * get list of products to show
     */
    fun getProductList(isRetry: Boolean = false) {
        CoroutineScope(IO).launch {
            mProductList.value = NetworkResponse.Loading
            mProductList.value = productRepository.getProductList()
        }
    }

}
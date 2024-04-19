package com.example.myproducts.ui.products

import com.example.myproducts.models.Product
import com.example.myproducts.network.NetworkResponse
import com.example.myproducts.network.ProductApiService
import com.example.myproducts.network.models.NetworkResourceError
import org.json.JSONObject
import retrofit2.Response


class ProductRepository(
    private val apiService: ProductApiService
) {

    suspend fun getProductList(): NetworkResponse<out Response<List<Product>>, out NetworkResourceError> {
        return try {
            val response = apiService.getProducts()
            if (response.isSuccessful) {
                NetworkResponse.Success(response)
            } else {
                val jsonObject = response.errorBody()?.string()?.let { JSONObject(it) }
                val errorMessage =
                    jsonObject?.toString() ?: "Something went wrong, Please try again later."
                NetworkResponse.Failure(NetworkResourceError(errorMessage))
            }
        } catch (e: Exception) {
            NetworkResponse.Failure(
                NetworkResourceError(
                    "Something went wrong",
                    e.stackTraceToString()
                )
            )
        }
    }

}
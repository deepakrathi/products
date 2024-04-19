package com.example.myproducts.network

import com.example.myproducts.models.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    /**
     * Get a list of products
     */
    @GET("interview-sandbox/android/json-to-list/products.v1.json")
    suspend fun getProducts(): Response<List<Product>>
}
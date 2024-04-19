package com.example.myproducts.network

import android.content.Context
import com.example.myproducts.MyProductApplication
import com.example.myproducts.R
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.KoinContext
import org.koin.dsl.koinApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient(private val context: Context) {

    fun build(): ProductApiService {
            val retrofit = Retrofit
                .Builder()
                .baseUrl(context.getString(R.string.baseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        return retrofit.create(ProductApiService::class.java)
    }

}
package com.example.myproducts.di

import com.example.myproducts.network.RetrofitClient
import com.example.myproducts.ui.products.ProductRepository
import com.example.myproducts.ui.products.ProductViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {

    val repositoryModules = module {
        single { RetrofitClient(androidContext()).build() }
        single { ProductRepository(get()) }
    }

    val viewModelModules = module {
        viewModel { ProductViewModel(get()) }
    }

}
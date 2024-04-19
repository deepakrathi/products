package com.example.myproducts.ui.products

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.collectAsState
import com.example.myproducts.ui.composables.product.ProductListComposable
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductActivity : AppCompatActivity() {

    private val viewModel: ProductViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set state based UI
        setContent {
            ProductListComposable(productListResponse = viewModel.productList.collectAsState().value) {
                // retry in case of failure
                viewModel.getProductList(isRetry = true)
            }
        }

        // get product list
        viewModel.getProductList()
    }

}
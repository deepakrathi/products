package com.example.myproducts.ui.composables.product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.myproducts.models.Product
import com.example.myproducts.network.NetworkResponse
import com.example.myproducts.network.models.NetworkResourceError
import com.example.myproducts.ui.composables.ErrorComposable
import com.example.myproducts.ui.composables.GenericLoadingComposable
import retrofit2.Response

@Composable
fun ProductListComposable(
    modifier: Modifier = Modifier,
    productListResponse: NetworkResponse<out Response<List<Product>>, out NetworkResourceError>?,
    onRetryClickAction: () -> Unit
) {

    // checking the state of the product list
    when (productListResponse) {
        // Loading state
        is NetworkResponse.Loading -> {
            GenericLoadingComposable()
        }

        // Response Success state
        is NetworkResponse.Success -> {
            productListResponse.data.body()?.let { productList ->
                LazyColumn(modifier = modifier) {
                    items(productList.size) {
                        ItemProduct(
                            Modifier
                                .padding(16.dp)
                                .background(
                                    color = Color.LightGray,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(16.dp),
                            productList[it]
                        )
                    }
                }
            }

        }

        // Failure state with retry case
        is NetworkResponse.Failure -> {
            ErrorComposable {
                // handle click here in case we want any retry strategy
                onRetryClickAction.invoke()
            }
        }

        null -> {
            // No operation
        }
    }

}


@Composable
fun ItemProduct(
    modifier: Modifier,
    product: Product
) {

    Column(modifier = modifier) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "Product Name : ${product.name}, ")
            Text(text = "Rating : ${product.rating}")
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text(text = product.tagline)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Date : ${product.date}")

    }

}
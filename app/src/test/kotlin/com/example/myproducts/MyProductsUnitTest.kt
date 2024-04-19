package com.example.myproducts

import com.example.myproducts.models.Product
import com.example.myproducts.network.NetworkResponse
import com.example.myproducts.network.models.NetworkResourceError
import com.example.myproducts.ui.products.ProductRepository
import com.example.myproducts.ui.products.ProductViewModel
import io.mockk.coEvery
import io.mockk.mockkClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MyProductsUnitTest {

    private lateinit var viewModel: ProductViewModel

    lateinit var prodcutsRepository: ProductRepository


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun init() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        prodcutsRepository = mockkClass(ProductRepository::class)
        viewModel = ProductViewModel(prodcutsRepository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getProductList() {
        runTest {
            // creating mocked response product list
            val loadingState = NetworkResponse.Loading
            val response = NetworkResponse.Success<Response<List<Product>>, NetworkResourceError>(
                Response.success(listOf(Product("Foo", "Tagline", "5", "16/03/2024")))
            )
            coEvery { prodcutsRepository.getProductList() } returns response

            //calling the function
            viewModel.getProductList()
            advanceUntilIdle()

            //validating loading state response
            Assert.assertEquals(loadingState, viewModel.productList.value)
            //validating response state
            Assert.assertEquals(response, viewModel.productList.drop(1).first())
        }
    }
}
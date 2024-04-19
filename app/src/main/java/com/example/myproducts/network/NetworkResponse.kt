package com.example.myproducts.network

sealed class NetworkResponse<Response, Error> {

    data class Success<Response, Error>(val data: Response) : NetworkResponse<Response, Error>()
    data class Failure<Response, Error>(val data: Error?) : NetworkResponse<Response, Error>()
    object Loading : NetworkResponse<Nothing, Nothing>()

}
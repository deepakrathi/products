package com.example.myproducts.network.models

data class NetworkResourceError(
    val errorMessage: String,
    val stackTrace: String? = null,
    val errorCode: Int? = null,
)

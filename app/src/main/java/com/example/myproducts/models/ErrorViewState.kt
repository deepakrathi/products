package com.example.myproducts.models

data class ErrorViewState(
    val title : String,
    val subTitle : String,
    val cta : Cta
)

enum class Cta() {
    Okay,
    Retry,
}
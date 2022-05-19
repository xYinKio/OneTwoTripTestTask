package com.example.onetwotriptesttask.data.pojo

import kotlinx.serialization.Serializable

@Serializable
data class Price(
    val amount: Int,
    val type: String
)
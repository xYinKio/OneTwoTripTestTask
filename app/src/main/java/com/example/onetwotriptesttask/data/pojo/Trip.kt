package com.example.onetwotriptesttask.data.pojo

import kotlinx.serialization.Serializable

@Serializable
data class Trip(
    val from: String,
    val to: String
)
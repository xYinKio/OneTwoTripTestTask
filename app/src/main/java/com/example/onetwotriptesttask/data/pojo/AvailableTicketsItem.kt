package com.example.onetwotriptesttask.data.pojo

import kotlinx.serialization.Serializable

@Serializable
data class AvailableTicketsItem(
    val currency: String,
    val prices: List<Price>,
    val trips: List<Trip>
)
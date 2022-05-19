package com.example.onetwotriptesttask.domain

data class AvailableTickets(
    val currency: String,
    val prices: Map<String, Int>,
    val trips: List<Trip>
)
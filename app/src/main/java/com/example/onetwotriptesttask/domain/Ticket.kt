package com.example.onetwotriptesttask.domain

import java.io.Serializable


data class Ticket(
    val currency: String,
    val price: Int,
    val flightClass: String,
    val trips: List<Trip>
) : Serializable
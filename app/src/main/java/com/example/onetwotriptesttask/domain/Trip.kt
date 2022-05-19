package com.example.onetwotriptesttask.domain

import java.io.Serializable


data class Trip(
    val from: String,
    val to: String
) : Serializable
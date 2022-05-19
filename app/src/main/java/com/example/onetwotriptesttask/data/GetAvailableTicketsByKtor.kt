package com.example.onetwotriptesttask.data

import com.example.onetwotriptesttask.data.pojo.AvailableTicketsItem
import com.example.onetwotriptesttask.domain.AvailableTickets
import com.example.onetwotriptesttask.domain.GetAvailableTickets
import com.example.onetwotriptesttask.domain.Trip
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAvailableTicketsByKtor @Inject constructor() : GetAvailableTickets {


    override fun invoke() = flow {
        val client = HttpClient(CIO){
            install(ContentNegotiation){
                json()
            }
        }

        val response = client.get("https://603e34c648171b0017b2ec55.mockapi.io/ott/search")
        val tickets: List<AvailableTicketsItem> = response.body()
        val res = tickets.map {
            AvailableTickets(
                currency = it.currency,
                prices = prices(it),
                trips = it.trips.map { Trip(it.from, it.to) }
            )
        }
        emit(res)
    }

    private fun prices(it: AvailableTicketsItem): Map<String, Int> {
        val prices = mutableMapOf<String, Int>()
        it.prices.forEach { price ->
            prices[price.type] = price.amount
        }
        return prices
    }
}
package com.example.onetwotriptesttask.ui.model.state

import com.example.onetwotriptesttask.domain.AvailableTickets
import com.example.onetwotriptesttask.domain.Ticket

sealed class AvailableTicketsState {
    open val allTickets: List<AvailableTickets> = listOf()

    data class NoChosen(override val allTickets: List<AvailableTickets>) : AvailableTicketsState()
    data class Chosen(override val allTickets: List<AvailableTickets>,
                      val tickets: AvailableTickets) : AvailableTicketsState()
    data class Completed(val ticket: Ticket) : AvailableTicketsState()
}
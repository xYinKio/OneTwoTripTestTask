package com.example.onetwotriptesttask.ui.model.event

import com.example.onetwotriptesttask.domain.AvailableTickets

sealed class AvailableTicketsEvent {
    data class ClickOnTickets(val tickets: AvailableTickets) : AvailableTicketsEvent()
    object DismissDialog : AvailableTicketsEvent()
    data class ChooseTicket(val flightClass: String) : AvailableTicketsEvent()
}
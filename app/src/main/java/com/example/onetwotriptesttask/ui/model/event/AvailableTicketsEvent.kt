package com.example.onetwotriptesttask.ui.model.event

import com.example.onetwotriptesttask.domain.AvailableTickets

sealed class AvailableTicketsEvent {
    data class ClickOnTickets(val tickets: AvailableTickets,
                              val index: Int
    ) : AvailableTicketsEvent()
    object DismissDialog : AvailableTicketsEvent()
    object Update : AvailableTicketsEvent()
}
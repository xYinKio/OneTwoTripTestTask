package com.example.onetwotriptesttask.ui.model.state

import com.example.onetwotriptesttask.domain.Ticket

sealed class ChosenTicketState {
    data class Init(val ticket: Ticket) : ChosenTicketState()
    object Empty : ChosenTicketState()
}
package com.example.onetwotriptesttask.ui.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.onetwotriptesttask.domain.GetAvailableTickets
import com.example.onetwotriptesttask.domain.Ticket
import com.example.onetwotriptesttask.ui.model.state.ChosenTicketState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChosenTicketViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getAvailableTickets: GetAvailableTickets
) : BaseViewModel<Any, ChosenTicketState>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val flightClass = savedStateHandle.get<String>("flight_class")!!
            val index = savedStateHandle.get<Int>("index")!!
            getAvailableTickets().collect{
                val available = it[index]
                val ticket = Ticket(
                    currency = available.currency,
                    price = available.prices[flightClass]!!,
                    flightClass = flightClass,
                    trips = available.trips
                )
                _state.emit(ChosenTicketState.Init(ticket))
            }
        }
    }

    override fun obtainEvent(event: Any) {}
}
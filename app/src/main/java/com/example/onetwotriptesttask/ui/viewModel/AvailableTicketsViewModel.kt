package com.example.onetwotriptesttask.ui.viewModel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.onetwotriptesttask.domain.GetAvailableTickets
import com.example.onetwotriptesttask.domain.Ticket
import com.example.onetwotriptesttask.ui.model.event.AvailableTicketsEvent
import com.example.onetwotriptesttask.ui.model.state.AvailableTicketsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableTicketsViewModel @Inject constructor(
    private val getAvailableTickets: GetAvailableTickets
) : BaseViewModel<AvailableTicketsEvent, AvailableTicketsState>() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAvailableTickets().collect{
                _state.emit(AvailableTicketsState.NoChosen(it))
            }
        }
    }

    override fun obtainEvent(event: AvailableTicketsEvent) {

        viewModelScope.launch(Dispatchers.IO) {
            when(event){
                is AvailableTicketsEvent.ClickOnTickets -> {
                    _state.emit(AvailableTicketsState.Chosen(
                        _state.first().allTickets, event.tickets
                    ))
                }
                is AvailableTicketsEvent.DismissDialog -> {
                    _state.emit(AvailableTicketsState.NoChosen(
                        _state.first().allTickets
                    ))
                }
                is AvailableTicketsEvent.ChooseTicket -> {
                    val availableTickets = (_state.first() as AvailableTicketsState.Chosen).tickets
                    _state.emit(AvailableTicketsState.Completed(
                        Ticket(
                            currency = availableTickets.currency,
                            price = availableTickets.prices[event.flightClass]!!,
                            trips = availableTickets.trips,
                            flightClass = event.flightClass
                        )
                    ))
                }
            }
        }
    }
}
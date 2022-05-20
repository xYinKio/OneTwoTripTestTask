package com.example.onetwotriptesttask.ui.viewModel

import androidx.lifecycle.viewModelScope
import com.example.onetwotriptesttask.domain.GetAvailableTickets
import com.example.onetwotriptesttask.ui.model.event.AvailableTicketsEvent
import com.example.onetwotriptesttask.ui.model.state.AvailableTicketsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AvailableTicketsViewModel @Inject constructor(
    private val getAvailableTickets: GetAvailableTickets
) : BaseViewModel<AvailableTicketsEvent, AvailableTicketsState>() {



    override fun obtainEvent(event: AvailableTicketsEvent) {

        viewModelScope.launch(Dispatchers.IO) {
            when(event){
                is AvailableTicketsEvent.ClickOnTickets -> {
                    _state.emit(AvailableTicketsState.Chosen(
                        allTickets = _state.first().allTickets,
                        tickets = event.tickets,
                        index = event.index
                    ))
                }
                is AvailableTicketsEvent.DismissDialog -> {
                    _state.emit(AvailableTicketsState.NoChosen(
                        _state.first().allTickets
                    ))
                }
                is AvailableTicketsEvent.Update -> { update() }

            }
        }
    }

    private suspend fun update() {
        getAvailableTickets().collect {
            _state.emit(AvailableTicketsState.NoChosen(it))
        }
    }
}
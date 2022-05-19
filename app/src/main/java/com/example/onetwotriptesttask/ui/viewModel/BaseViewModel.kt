package com.example.onetwotriptesttask.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*


abstract class BaseViewModel<Event, State> : ViewModel() {

    protected val _state = MutableSharedFlow<State>(
        replay = 1
    )
    val state = _state.shareIn(viewModelScope, SharingStarted.Lazily)

    abstract fun obtainEvent(event: Event)
}
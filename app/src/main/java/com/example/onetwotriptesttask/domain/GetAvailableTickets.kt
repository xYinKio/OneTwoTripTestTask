package com.example.onetwotriptesttask.domain

import kotlinx.coroutines.flow.Flow

interface GetAvailableTickets : () -> Flow<List<AvailableTickets>>
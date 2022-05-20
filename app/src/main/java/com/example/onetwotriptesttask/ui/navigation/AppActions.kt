package com.example.onetwotriptesttask.ui.navigation

import androidx.navigation.NavController

class AppActions(navController: NavController){
    val goBack: () -> Unit = {
        navController.navigateUp()
    }
    val goToAvailableTickets: () -> Unit = {
        navController.navigate(Destinations.AVAILABLE_TICKETS)
    }
    val goToChosenTicket: (flightClass: String, index: Int) -> Unit = {flightClass, index ->
        navController.navigate(Destinations.CHOSEN_TICKET +
            "/$flightClass/$index"
        )
    }
}
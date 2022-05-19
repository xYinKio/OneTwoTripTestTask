package com.example.onetwotriptesttask.ui.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.onetwotriptesttask.domain.AvailableTickets
import com.example.onetwotriptesttask.domain.Ticket

class AppActions(navController: NavController){
    val goBack: () -> Unit = {
        navController.navigateUp()
    }
    val goToAvailableTickets: () -> Unit = {
        navController.navigate(Destinations.AVAILABLE_TICKETS)
    }
    val goToChosenTicket: (Ticket) -> Unit = {
        navController.currentBackStackEntry!!.arguments!!.putSerializable("chosen_ticket", it)
        navController.navigate(Destinations.CHOSEN_TICKET )
    }
}
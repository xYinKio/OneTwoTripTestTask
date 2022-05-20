package com.example.onetwotriptesttask.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onetwotriptesttask.domain.Ticket
import com.example.onetwotriptesttask.ui.screens.*


@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destinations.HOME
){
    val actions = AppActions(navController)
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ){

        composable(Destinations.HOME){ HomeRoute(actions) }
        composable(Destinations.AVAILABLE_TICKETS){ AvailableTicketsRoute(actions) }
        composable(Destinations.CHOSEN_TICKET+ "/{flight_class}/{index}" ,
            arguments = listOf(
                navArgument("flight_class"){ type = NavType.StringType },
                navArgument("index"){type = NavType.IntType}
            )
        ){
            ChosenTicketRoute()
        }
    }
}
package com.example.onetwotriptesttask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onetwotriptesttask.domain.AvailableTickets
import com.example.onetwotriptesttask.domain.Ticket
import com.example.onetwotriptesttask.domain.Trip
import com.example.onetwotriptesttask.ui.airportByCode
import com.example.onetwotriptesttask.ui.model.event.AvailableTicketsEvent
import com.example.onetwotriptesttask.ui.model.state.AvailableTicketsState
import com.example.onetwotriptesttask.ui.navigation.AppActions
import com.example.onetwotriptesttask.ui.theme.AppTheme
import com.example.onetwotriptesttask.ui.viewModel.AvailableTicketsViewModel

@Composable
fun AvailableTicketsRoute(actions: AppActions){
    hiltViewModel<AvailableTicketsViewModel>().apply {
        AvailableTicketsScreen(
            state = state.collectAsState(
                initial = AvailableTicketsState.NoChosen(listOf())
            ).value,
            onDismissDialog = { obtainEvent(AvailableTicketsEvent.DismissDialog) },
            onClickTickets = { obtainEvent(AvailableTicketsEvent.ClickOnTickets(it))},
            onAcceptDialog = { obtainEvent(AvailableTicketsEvent.ChooseTicket(it)) },
            onSelectTicket = actions.goToChosenTicket
        )
    }

}

@Composable
fun AvailableTicketsScreen(
    modifier: Modifier = Modifier,
    state: AvailableTicketsState = AvailableTicketsState.NoChosen(listOf()),
    onDismissDialog: () -> Unit = {},
    onClickTickets: (AvailableTickets) -> Unit = {},
    onSelectTicket: (Ticket) -> Unit = {},
    onAcceptDialog: (String) -> Unit = {}
){

    if (state is AvailableTicketsState.Completed){
        onSelectTicket(state.ticket)
    }

    if (state is AvailableTicketsState.Chosen){
        ChoosePriceDialog(
            prices = state.tickets.prices,
            currency = state.tickets.currency,
            onDismiss = onDismissDialog,
            onSelect = onAcceptDialog
        )
    }


    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(state.allTickets){
            AvailableTicketsItem(
                availableTickets = it,
                onClick = onClickTickets
            )
        }
    }
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AvailableTicketsItem(
    modifier: Modifier = Modifier,
    availableTickets: AvailableTickets,
    onClick: (AvailableTickets) -> Unit = {}
){
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick(availableTickets)
        }
    ) {

        Column(
            Modifier.padding(8.dp)
        ) {
            
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ProvideTextStyle(value = MaterialTheme.typography.h6) {
                    Text(text = airportByCode[availableTickets.trips.first().from]!!,
                        color = MaterialTheme.colors.primaryVariant
                    )

                    Image(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        imageVector = Icons.Filled.ArrowForward,
                        contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )

                    Text(text = airportByCode[availableTickets.trips.last().to]!!,
                        color = MaterialTheme.colors.secondaryVariant
                    )
                }

            }
            
            Row {
                Text(text = "Количество пересадок:")
                Text(
                    modifier = Modifier.padding(start = 8.dp),
                    text = availableTickets.trips.size.toString())
            }
            
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd) {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    if (availableTickets.prices.size > 1){
                        Text(
                            modifier = Modifier.padding(end = 8.dp),
                            text = "от")
                    }
                    Text(
                        text = availableTickets.prices.minOf { it.value }.toString(),
                        style = MaterialTheme.typography.h5
                    )
                }

            }

        }



    }
}

@Composable
@Preview
fun AvailableTicketsPreview(){
    AppTheme {
        val tickets = mutableListOf<AvailableTickets>()
        for (i in 0..10){
            tickets.add(AvailableTickets(
                currency = "RUB",
                prices = mapOf(
                    "business" to 49673,
                    "economy" to 29573
                ),
                trips = listOf(
                    Trip("SVO", "HND"),
                    Trip("NRT", "EWR")
                )
            ))
        }
        AvailableTicketsScreen(state = AvailableTicketsState.NoChosen(tickets))
    }

}

@Composable
@Preview
fun AvailableTicketsItemPreview(){
    AppTheme {
        AvailableTicketsItem(
            modifier = Modifier.padding(16.dp),
            availableTickets = AvailableTickets(
                currency = "RUB",
                prices = mapOf(
                    "business" to 49673,
                    "economy" to 29573
                ),
                trips = listOf(
                    Trip("SVO", "HND"),
                    Trip("NRT", "EWR")
                )
            )
        )
    }
}
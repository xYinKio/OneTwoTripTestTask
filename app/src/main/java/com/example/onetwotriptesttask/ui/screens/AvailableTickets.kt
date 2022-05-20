package com.example.onetwotriptesttask.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onetwotriptesttask.domain.AvailableTickets
import com.example.onetwotriptesttask.domain.Trip
import com.example.onetwotriptesttask.ui.airportByCode
import com.example.onetwotriptesttask.ui.model.event.AvailableTicketsEvent
import com.example.onetwotriptesttask.ui.model.state.AvailableTicketsState
import com.example.onetwotriptesttask.ui.navigation.AppActions
import com.example.onetwotriptesttask.ui.theme.AppTheme
import com.example.onetwotriptesttask.ui.viewModel.AvailableTicketsViewModel
import com.example.onetwotriptesttask.ui.widgets.ArrowImage

@Composable
fun AvailableTicketsRoute(actions: AppActions){
    hiltViewModel<AvailableTicketsViewModel>().apply {
        LaunchedEffect(Unit){
            obtainEvent(AvailableTicketsEvent.Update)
        }

        AvailableTicketsScreen(
            modifier = Modifier.fillMaxSize(),
            state = state.collectAsState(
                initial = AvailableTicketsState.NoChosen(listOf())
            ).value,
            onDismissDialog = { obtainEvent(AvailableTicketsEvent.DismissDialog) },
            onClickTickets = {item, index ->
                obtainEvent(AvailableTicketsEvent.ClickOnTickets(item, index))},
            onAcceptDialog = actions.goToChosenTicket,
        )
    }

}

@Composable
fun AvailableTicketsScreen(
    modifier: Modifier = Modifier,
    state: AvailableTicketsState = AvailableTicketsState.NoChosen(listOf()),
    onDismissDialog: () -> Unit = {},
    onClickTickets: (AvailableTickets, index: Int) -> Unit = {_,_->},
    onAcceptDialog: (category: String, index: Int) -> Unit = {_,_->}
){


    if (state is AvailableTicketsState.Chosen){
        ChoosePriceDialog(
            prices = state.tickets.prices,
            currency = state.tickets.currency,
            onDismiss = onDismissDialog,
            onSelect = onAcceptDialog,
            index = state.index
        )
    }


    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        itemsIndexed(state.allTickets){index, item ->
            AvailableTicketsItem(
                availableTickets = item,
                onClick = onClickTickets,
                index = index
            )
        }
    }
}




@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AvailableTicketsItem(
    modifier: Modifier = Modifier,
    availableTickets: AvailableTickets,
    index: Int = 0,
    onClick: (AvailableTickets, index: Int) -> Unit = {_,_->}
){
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick(availableTickets, index)
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

                    ArrowImage()

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
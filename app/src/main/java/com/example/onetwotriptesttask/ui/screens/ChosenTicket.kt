package com.example.onetwotriptesttask.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.onetwotriptesttask.domain.Ticket
import com.example.onetwotriptesttask.domain.Trip
import com.example.onetwotriptesttask.ui.airportByCode
import com.example.onetwotriptesttask.ui.model.state.ChosenTicketState
import com.example.onetwotriptesttask.ui.theme.AppTheme
import com.example.onetwotriptesttask.ui.viewModel.ChosenTicketViewModel
import com.example.onetwotriptesttask.ui.widgets.ArrowImage

@Composable
fun ChosenTicketRoute(){
    hiltViewModel<ChosenTicketViewModel>().apply {
        ChosenTicket(
            modifier = Modifier.fillMaxSize(),
            state = state.collectAsState(ChosenTicketState.Empty).value)
    }

}

@Composable
fun ChosenTicket(
    modifier: Modifier = Modifier,
    state: ChosenTicketState
){

        Surface(
            modifier = modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            if (state is ChosenTicketState.Init) {

                Column {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ProvideTextStyle(
                            value = MaterialTheme.typography.h5
                        ) {
                            Text(
                                text = airportByCode[state.ticket.trips.first().from]!!,
                                color = MaterialTheme.colors.primaryVariant
                            )
                            ArrowImage()
                            Text(
                                text = airportByCode[state.ticket.trips.last().to]!!,
                                color = MaterialTheme.colors.secondaryVariant
                            )
                        }

                    }

                    Divider(
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        )
                    )


                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Card(
                            modifier = Modifier,
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(8.dp)
                            ) {
                                ProvideTextStyle(value = LocalTextStyle.current) {
                                    state.ticket.trips.forEach {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.Center
                                        ) {
                                            Text(
                                                modifier = Modifier.weight(1f),
                                                text = airportByCode[it.from]!!
                                            )
                                            ArrowImage()
                                            Text(
                                                modifier = Modifier.weight(1f),
                                                text = airportByCode[it.to]!!
                                            )
                                        }
                                    }
                                }
                            }
                        }


                        Column(
                            horizontalAlignment = Alignment.End
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    modifier = Modifier.alignByBaseline(),
                                    text = state.ticket.price.toString(),
                                    fontSize = 24.sp,
                                    color = MaterialTheme.colors.secondaryVariant
                                )
                                Text(
                                    modifier = Modifier
                                        .alignByBaseline()
                                        .padding(start = 8.dp),
                                    text = state.ticket.currency,
                                    color = MaterialTheme.colors.primaryVariant
                                )
                            }

                            Text(text = state.ticket.flightClass,)
                        }

                    }


                }
            }

        }


}

@Composable
@Preview
private fun ChosenTicketPreview(){
    AppTheme {
        ChosenTicket(
            state = ChosenTicketState.Init(
                ticket = Ticket(
                    currency = "RUB",
                    price = 49673,
                    flightClass = "business",
                    trips = listOf(
                        Trip("SVO", "HND"),
                        Trip("NRT", "EWR")
                    )
                )
            )
        )
    }
}
package com.example.onetwotriptesttask.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onetwotriptesttask.domain.Ticket
import com.example.onetwotriptesttask.domain.Trip
import com.example.onetwotriptesttask.ui.airportByCode
import com.example.onetwotriptesttask.ui.theme.AppIcons
import com.example.onetwotriptesttask.ui.theme.AppTheme

@Composable
fun ChosenTicketRoute(
    ticket: Ticket
){
    ChosenTicket(ticket = ticket)
}

@Composable
fun ChosenTicket(
    modifier: Modifier = Modifier,
    ticket: Ticket
){
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.background
    ) {
        Column {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ProvideTextStyle(
                    value = MaterialTheme.typography.h5
                ) {
                    Text(text = airportByCode[ticket.trips.first().from]!!,
                        color = MaterialTheme.colors.primaryVariant
                    )
                    Image(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        imageVector = AppIcons.ArrowForward,
                        contentDescription = "")
                    Text(text = airportByCode[ticket.trips.last().to]!!,
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
            
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Card(
                    modifier = Modifier
                        .weight(1.8f),
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        ProvideTextStyle(value = LocalTextStyle.current) {
                            ticket.trips.forEach {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = airportByCode[it.from]!!)
                                    Image(
                                        modifier = Modifier.padding(8.dp),
                                        imageVector = AppIcons.ArrowForward,
                                        contentDescription = "",
                                    )
                                    Text(
                                        modifier = Modifier.weight(1f),
                                        text = airportByCode[it.to]!!)
                                }
                            }
                        }
                    }
                }


                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(
                            modifier = Modifier.alignByBaseline(),
                            text = ticket.price.toString(),
                            fontSize = 24.sp,
                            color = MaterialTheme.colors.secondary
                        )
                        Text(
                            modifier = Modifier
                                .alignByBaseline()
                                .padding(start = 8.dp),
                            text = ticket.currency,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }

                    Text(text = ticket.flightClass,)
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
    }
}
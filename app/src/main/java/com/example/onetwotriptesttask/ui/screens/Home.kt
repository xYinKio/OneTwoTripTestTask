package com.example.onetwotriptesttask.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.onetwotriptesttask.ui.navigation.AppActions
import com.example.onetwotriptesttask.ui.theme.AppTheme


@Composable
fun HomeRoute(actions: AppActions){
    Home(
        onShowTicketsClick = actions.goToAvailableTickets
    )
}

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onShowTicketsClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    style = MaterialTheme.typography.h5,
                    text = "AviaSales",
                )
            }
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Button(onClick = onShowTicketsClick) {
                    Text(text = "Show tickets")
                }

            }
        }
    }
}

@Preview
@Composable
fun MainPreview(){
    AppTheme {
        Home()
    }
}

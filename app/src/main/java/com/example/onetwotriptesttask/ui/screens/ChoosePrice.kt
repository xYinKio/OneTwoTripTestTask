package com.example.onetwotriptesttask.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.onetwotriptesttask.ui.theme.AppTheme

@Composable
fun ChoosePriceDialog(
    prices: Map<String, Int>,
    currency: String,
    onDismiss: () -> Unit,
    onSelect: (category: String) -> Unit = {}
){
    Dialog(onDismissRequest = onDismiss) {
        ChoosePrice(
            prices = prices,
            currency = currency,
            onSelect = onSelect)
    }
}


@Composable
fun ChoosePrice(
    modifier: Modifier = Modifier,
    prices: Map<String, Int>,
    currency: String,
    onSelect: (category: String) -> Unit = {}
){
    val selected = remember{ mutableStateOf("business") }

    Card(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.background) {

        Column(
            modifier = Modifier.padding(8.dp),
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(prices.keys.toList()){
                    Item(it, selected, prices, currency)
                }
            }

            Box(
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.BottomEnd
            ) {
                Button(onClick = {
                    onSelect(selected.value)
                },
                    colors = buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSecondary
                    )
                ) {
                    Text(text = "Выбрать")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Item(
    it: String,
    selected: MutableState<String>,
    prices: Map<String, Int>,
    currency: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor =
        if (it == selected.value) MaterialTheme.colors.primary
        else MaterialTheme.colors.surface,
        onClick = { selected.value = it }
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = it
            )
            Box {
                Row(
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = prices[it].toString(),
                        modifier = Modifier.alignByBaseline()
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .alignByBaseline(),
                        text = currency,
                        fontSize = 12.sp,
                        style = MaterialTheme.typography.subtitle2,
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun ChoosePricePreview(){
    AppTheme {
        ChoosePrice(
            modifier = Modifier.padding(16.dp),
            prices = mapOf(
                "business" to 34256,
                "econom" to 25322
            ),
            currency = "RUB"
        )
    }
}


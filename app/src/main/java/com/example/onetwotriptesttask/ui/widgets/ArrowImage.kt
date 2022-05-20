package com.example.onetwotriptesttask.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.example.onetwotriptesttask.ui.theme.AppIcons

@Composable
fun ArrowImage(){
    Image(
        modifier = Modifier.padding(8.dp),
        imageVector = AppIcons.ArrowForward,
        contentDescription = "",
        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
    )
}
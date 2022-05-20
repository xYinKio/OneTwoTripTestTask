package com.example.onetwotriptesttask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.onetwotriptesttask.ui.navigation.NavGraph
import com.example.onetwotriptesttask.ui.screens.Home
import com.example.onetwotriptesttask.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    NavGraph()
                }

            }
        }
    }


}
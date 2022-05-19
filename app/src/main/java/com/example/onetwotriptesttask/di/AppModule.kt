package com.example.onetwotriptesttask.di

import com.example.onetwotriptesttask.data.GetAvailableTicketsByKtor
import com.example.onetwotriptesttask.domain.GetAvailableTickets
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface AppModule {

    @Binds
    fun provideGetAvailableTickets(get: GetAvailableTicketsByKtor ) : GetAvailableTickets

}
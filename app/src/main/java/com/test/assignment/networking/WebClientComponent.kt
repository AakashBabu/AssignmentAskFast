package com.test.assignment.networking

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(WebClientModule::class))
interface WebClientComponent {

    fun getApiService(): WebClientService
}
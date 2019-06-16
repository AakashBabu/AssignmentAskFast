package com.test.assignment.application

import android.app.Application
import com.test.assignment.networking.DaggerWebClientComponent
import com.test.assignment.networking.WebClientComponent
import com.test.assignment.networking.WebClientModule
import com.test.assignment.networking.WebClientService
import retrofit2.Retrofit

class MyApplication : Application() {

    lateinit var webComponent : WebClientComponent

    override fun onCreate() {
        super.onCreate()
        webComponent = DaggerWebClientComponent.builder()
            .webClientModule(WebClientModule()).build()
    }

    fun getWebserviceCall(): WebClientService {
        return webComponent.getApiService()
    }

}
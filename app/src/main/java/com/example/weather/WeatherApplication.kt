package com.example.weather

import android.app.Application
import android.content.Context

class WeatherApplication : Application() {
    companion object{
        const val TOKEN = "VkV5hGf8BWpAO0Eu"
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
package com.example.cytrack

import android.app.Application
import com.example.cytrack.di.AppComponent
import com.example.cytrack.di.DaggerAppComponent


class CyberTrackerAppApplication
    : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}

package com.example.cytrack

import android.app.Application
import com.example.cytrack.di.AppComponent
import com.example.cytrack.di.DaggerAppComponent


class CyberTrackerAppApplication
    : Application() {

    val appComponent: AppComponent = createAppComponent()

    private fun createAppComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .build()
    }
}


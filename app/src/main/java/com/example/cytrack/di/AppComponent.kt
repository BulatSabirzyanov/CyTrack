package com.example.cytrack.di


import android.app.Application



import com.example.cytrack.presentation.screens.schedulefragment.ScheduleFragment
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class])
interface AppComponent {


    fun inject(scheduleFragment: ScheduleFragment)

    fun scheduleFragmentViewModel(): ScheduleFragmentViewModel.Factory


    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}

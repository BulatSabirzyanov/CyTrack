package com.example.cytrack.di


import android.app.Application



import com.example.cytrack.presentation.screens.schedulefragment.ScheduleFragment
import com.example.cytrack.presentation.screens.searchfragment.SearchFragment
import com.example.cytrack.presentation.viewmodel.ScheduleFragmentViewModel
import com.example.cytrack.presentation.viewmodel.SearchFragmentViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, PresentationModule::class, DomainModule::class])
interface AppComponent {
    fun inject(scheduleFragment: ScheduleFragment)
    fun inject(searchFragment: SearchFragment)
    fun scheduleFragmentViewModel(): ScheduleFragmentViewModel.Factory
    fun searchFragmentViewModel(): SearchFragmentViewModel.Factory

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}

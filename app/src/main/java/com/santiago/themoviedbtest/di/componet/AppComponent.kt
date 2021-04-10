package com.santiago.themoviedbtest.di.componet

import android.app.Application
import com.santiago.themoviedbtest.AppController
import com.santiago.themoviedbtest.di.module.*
import com.santiago.themoviedbtest.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        DbModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentBuildersModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
    fun inject(appController: AppController)
}
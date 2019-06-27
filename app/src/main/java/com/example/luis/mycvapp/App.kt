package com.example.luis.mycvapp

import android.app.Activity
import android.app.Application
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.luis.mycvapp.dagger.AppInjector
import com.example.luis.mycvapp.dagger.component.AppComponent
import com.example.luis.mycvapp.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasSupportFragmentInjector {
    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private var appComponent: AppComponent? = null

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentInjector


    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

    }

    fun getAppComponent() = appComponent ?: DaggerAppComponent.builder()
        .application(this)
        .build()
        .also { appComponent = it }
}
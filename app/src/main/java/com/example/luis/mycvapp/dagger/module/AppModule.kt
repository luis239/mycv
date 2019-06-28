package com.example.luis.mycvapp.dagger.module


import com.example.luis.domain.common.executor.PostExecutionThread
import com.example.luis.mycvapp.MainActivity
import com.example.luis.mycvapp.common.UiThread
import com.example.luis.mycvapp.education.EducationInfoActivity
import com.example.luis.mycvapp.personalinfo.PersonalInfoActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun bindPersonalInfoActivity():PersonalInfoActivity

    @ContributesAndroidInjector
    abstract fun bindMainActivity():MainActivity

    @ContributesAndroidInjector
    abstract fun bindEducationActivity():EducationInfoActivity
}
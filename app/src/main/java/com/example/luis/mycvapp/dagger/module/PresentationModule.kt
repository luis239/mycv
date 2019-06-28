package com.example.luis.mycvapp.dagger.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.luis.mycvapp.common.ViewModelFactory
import com.example.luis.mycvapp.education.EducationViewModel
import com.example.luis.mycvapp.personalinfo.viewmodel.PersonalInfoViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(PersonalInfoViewModel::class)
    abstract fun bindPersonalInfoViewModel(viewModel: PersonalInfoViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EducationViewModel::class)
    abstract fun bindEducationViewModel(viewModel: EducationViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
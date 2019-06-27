package com.example.luis.mycvapp.dagger.module

import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.repository.PersonalInfoRepositoryImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DomainModule {

    @Binds
    abstract fun bindPersonalInfoRepository(personalInfoRepositoryImpl: PersonalInfoRepositoryImpl): PersonalInfoRepository
}
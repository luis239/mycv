package com.example.luis.repository.personalinfo.repository

import com.example.luis.repository.personalinfo.model.PersonalInformationData
import io.reactivex.Completable
import io.reactivex.Maybe

interface PersonalInfoCache {
    fun savePersonalInfo(personalInformationData: PersonalInformationData):Completable
    fun getPersonalInfo(): Maybe<PersonalInformationData>
}
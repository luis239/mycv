package com.example.luis.repository.personalinfo.repository

import com.example.luis.repository.personalinfo.model.PersonalInformationData
import io.reactivex.Completable

interface PersonalInfoCache {
    fun savePersonalInfo(personalInformationData: PersonalInformationData):Completable
}
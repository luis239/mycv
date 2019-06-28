package com.example.luis.domain.personalinfo.repository

import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import io.reactivex.Completable
import io.reactivex.Maybe

interface PersonalInfoRepository {
    fun saveInfo(personalInfo: PersonalInformationModel):Completable
    fun getPersonalInfo(): Maybe<PersonalInformationModel>

}
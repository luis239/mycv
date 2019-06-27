package com.example.luis.domain.personalinfo.repository

import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import io.reactivex.Completable

interface PersonalInfoRepository {
    fun saveInfo(personalInfo: PersonalInformationModel):Completable

}
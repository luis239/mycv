package com.example.luis.repository

import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.repository.personalinfo.mapper.mapToData
import com.example.luis.repository.personalinfo.repository.PersonalInfoCache
import io.reactivex.Completable
import javax.inject.Inject


class PersonalInfoRepositoryImpl  @Inject constructor(
    private val personalInfoCache: PersonalInfoCache
): PersonalInfoRepository {
    override fun saveInfo(personalInfo: PersonalInformationModel): Completable {
        return personalInfoCache.savePersonalInfo(personalInfo.mapToData())
    }

}
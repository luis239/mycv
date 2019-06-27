package com.example.luis.cache.personalinfo

import com.example.luis.cache.personalinfo.mapper.mapToCache
import com.example.luis.cache.room.AppDb
import com.example.luis.repository.personalinfo.model.PersonalInformationData
import com.example.luis.repository.personalinfo.repository.PersonalInfoCache
import io.reactivex.Completable
import javax.inject.Inject

class PersonalInfoCacheImple @Inject constructor(
    //private val appDb: AppDb
) : PersonalInfoCache {
    override fun savePersonalInfo(personalInformationData: PersonalInformationData): Completable {
        return Completable.defer {
            //appDb.personalInfoDao().insertInfo(personalInformationData.mapToCache())
            Completable.complete()
        }
    }
}
package com.example.luis.cache.education

import com.example.luis.cache.education.mapper.mapToData
import com.example.luis.cache.education.mapper.mapToEntity
import com.example.luis.cache.room.AppDb
import com.example.luis.repository.education.EducationCache
import com.example.luis.repository.education.EducationData
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

class EducationCacheImple @Inject constructor(
    private val appDb: AppDb
) : EducationCache {
    override fun saveEducation(educationData: List<EducationData>): Completable {
        return Completable.defer {
            appDb.educationDao().insertInfo(educationData.map {
                it.mapToEntity()
            })
        }


    }

    override fun getEducationalData(): Maybe<List<EducationData>> {
        return appDb.educationDao().getAll().flatMapMaybe {
            Maybe.just(it.map {
                it.mapToData()
            })
        }
    }
}
package com.example.luis.repository.education

import com.example.luis.domain.education.model.EducationModel
import com.example.luis.domain.education.repository.EducationRepository
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.domain.personalinfo.repository.PersonalInfoRepository
import com.example.luis.repository.personalinfo.mapper.mapToData
import com.example.luis.repository.personalinfo.mapper.mapToModel
import com.example.luis.repository.personalinfo.repository.PersonalInfoCache
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject


class EducationRepositoryImpl  @Inject constructor(
    private val educationCache: EducationCache
): EducationRepository {
    override fun saveInfo(educationModel: List<EducationModel>): Completable {
        return educationCache.saveEducation(educationModel.map {
            it.mapToData()
        })
    }

    override fun getEducation(): Maybe<List<EducationModel>> {
        return educationCache.getEducationalData().map {
            it.map {
                it.mapToModel()
            }
        }
    }

}
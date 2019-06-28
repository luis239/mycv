package com.example.luis.domain.education.repository

import com.example.luis.domain.education.model.EducationModel
import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import io.reactivex.Completable
import io.reactivex.Maybe

interface EducationRepository {
    fun saveInfo(educationModel: List<EducationModel>):Completable
    fun getEducation(): Maybe<List<EducationModel>>

}
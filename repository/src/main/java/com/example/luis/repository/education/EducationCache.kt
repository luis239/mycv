package com.example.luis.repository.education

import com.example.luis.repository.personalinfo.model.PersonalInformationData
import io.reactivex.Completable
import io.reactivex.Maybe

interface EducationCache {
    fun saveEducation(educationData: List<EducationData>):Completable
    fun getEducationalData(): Maybe<List<EducationData>>
}
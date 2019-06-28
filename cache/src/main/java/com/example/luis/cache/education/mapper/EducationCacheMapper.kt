package com.example.luis.cache.education.mapper

import com.example.luis.cache.education.EducationEntity
import com.example.luis.repository.education.EducationData

fun EducationData.mapToEntity() : EducationEntity{
    return EducationEntity(id, userId, degree, university, year)
}

fun EducationEntity.mapToData() : EducationData{
    return EducationData(id, userId, degree, university, year)
}
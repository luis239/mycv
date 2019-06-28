package com.example.luis.repository.education

import com.example.luis.domain.education.model.EducationModel

fun EducationData.mapToModel() : EducationModel{
    return EducationModel(id, userId, degree, university, year)
}

fun EducationModel.mapToData() : EducationData{
    return EducationData(id, userId, degree, university, year)
}
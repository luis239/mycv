package com.example.luis.repository.personalinfo.mapper

import com.example.luis.domain.personalinfo.model.PersonalInformationModel
import com.example.luis.repository.personalinfo.model.PersonalInformationData


fun PersonalInformationModel.mapToData() : PersonalInformationData{
    return PersonalInformationData(id, name, email, phone, image,birthday)
}

fun PersonalInformationData.mapToModel() : PersonalInformationModel{
    return PersonalInformationModel(id, name, email, phone, image,birthday)
}
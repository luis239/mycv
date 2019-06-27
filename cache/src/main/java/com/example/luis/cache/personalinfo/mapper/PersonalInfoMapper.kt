package com.example.luis.cache.personalinfo.mapper

import com.example.luis.cache.personalinfo.PersonalInformationEntity
import com.example.luis.repository.personalinfo.model.PersonalInformationData

fun PersonalInformationData.mapToCache():PersonalInformationEntity{
    return PersonalInformationEntity(id, name, email, phone, image)
}
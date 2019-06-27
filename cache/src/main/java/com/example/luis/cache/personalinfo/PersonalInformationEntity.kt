package com.example.luis.cache.personalinfo

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = PersonalInfoConstants.TABLE_NAME)
data class PersonalInformationEntity (
    @PrimaryKey(autoGenerate = false)
    var id: String = UUID.randomUUID().toString(),
    var name:String,
    var email:String,
    var phone: String,
    var image: String
)
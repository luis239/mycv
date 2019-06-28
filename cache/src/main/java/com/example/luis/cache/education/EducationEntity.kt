package com.example.luis.cache.education

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.example.luis.cache.personalinfo.PersonalInformationEntity
import java.util.*

@Entity(tableName = EducationInfoConstants.TABLE_NAME)
data class EducationEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ForeignKey(entity = PersonalInformationEntity::class,
            parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = CASCADE)
    var userId:String,
    var degree:String,
    var university:String,
    var year: String
)
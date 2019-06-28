package com.example.luis.cache.education.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.luis.cache.education.EducationEntity
import com.example.luis.cache.education.EducationInfoConstants
import com.example.luis.cache.personalinfo.PersonalInfoConstants
import com.example.luis.cache.personalinfo.PersonalInformationEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class EducationDao {

    @Query(EducationInfoConstants.GET_ALL)
    abstract fun getAll(): Single<List<EducationEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertInfo(educationEntity: List<EducationEntity>):Completable


}

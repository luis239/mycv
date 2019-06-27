package com.example.luis.cache.personalinfo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.luis.cache.personalinfo.PersonalInfoConstants
import com.example.luis.cache.personalinfo.PersonalInformationEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
abstract class PersonalInfoDao {

    @Query(PersonalInfoConstants.GET_ALL)
    abstract fun getAll(): Single<List<PersonalInformationEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertInfo(personalInformationEntity: PersonalInformationEntity):Completable


}

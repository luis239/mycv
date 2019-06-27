package com.example.luis.mycvapp.dagger.module

import android.content.Context
import com.example.luis.cache.personalinfo.PersonalInfoCacheImple
import com.example.luis.cache.room.AppDb
import com.example.luis.repository.personalinfo.repository.PersonalInfoCache
import dagger.Binds
import dagger.Module
import dagger.Provides
@Module
abstract class CacheModule {

    /*@Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(context: Context): AppDb {
            return AppDb.getInstance(context)
        }
    }*/

    @Binds
    abstract fun bindPersonalInfoCache(personalInfoCache: PersonalInfoCacheImple): PersonalInfoCache
}
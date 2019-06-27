package com.example.luis.mycvapp.common

import com.example.luis.domain.common.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class UiThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler get() = AndroidSchedulers.mainThread()

}
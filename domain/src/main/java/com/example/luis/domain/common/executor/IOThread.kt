package com.example.luis.domain.common.executor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IOThread @Inject constructor() : PostExecutionThread {

    override val scheduler: Scheduler get() = Schedulers.io()

}
package com.example.luis.domain.common.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    val scheduler: Scheduler
}

package com.example.luis.interactor

import com.example.luis.domain.common.executor.PostExecutionThread
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import io.reactivex.schedulers.Schedulers

abstract class MaybeUseCase<T, in Params> constructor(
        private val postExecutionThread: PostExecutionThread
) {

    private val disposables = CompositeDisposable()

    protected abstract fun buildUseCaseObservable(params: Params? = null): Maybe<T>

    open fun execute(maybeObserver: DisposableMaybeObserver<T>, params: Params? = null) {
        val maybe = this.buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(postExecutionThread.scheduler)
        addDisposable(maybe.subscribeWith(maybeObserver))
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun dispose() {
        if (!disposables.isDisposed) disposables.dispose()
    }

}
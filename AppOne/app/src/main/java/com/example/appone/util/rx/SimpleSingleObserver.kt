package com.example.appone.util.rx

import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import timber.log.Timber

abstract class SimpleSingleObserver<T>: SingleObserver<T> {

    private var disposable: Disposable? = null

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onSuccess(response: T) {
        onSuccesful(response)
        disposable?.dispose()
    }

    override fun onError(throwable: Throwable) {
        Timber.e(throwable)
        onError(throwable.message.orEmpty())
    }

    abstract fun onSuccesful(response: T)

    abstract fun onError(message: String)
}
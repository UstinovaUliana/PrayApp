package com.example.appone.domain.base

interface UseCase<T, U> {
    fun execute(param: T): U
}
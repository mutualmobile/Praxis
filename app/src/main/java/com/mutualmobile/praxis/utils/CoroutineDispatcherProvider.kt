package com.mutualmobile.praxis.utils

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineDispatcherProvider {
    fun io(): CoroutineDispatcher
    fun main(): CoroutineDispatcher
}
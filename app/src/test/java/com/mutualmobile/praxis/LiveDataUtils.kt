package com.mutualmobile.praxis

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


/**
 * This function observes a LiveData until it receives a new value (via onChanged)
 * and then it removes the observer. If the LiveData already has a value, it returns it immediately.
 * Additionally, if the value is never set, it will throw an exception after 2 seconds
 * (or whatever you set). This prevents tests that never finish when something goes wrong.
 */

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
  var data: T? = null
  val latch = CountDownLatch(1)
  val observer = object : Observer<T> {
    override fun onChanged(o: T?) {
      data = o
      latch.countDown()
      this@getOrAwaitValue.removeObserver(this)
    }
  }
  this.observeForever(observer)

  try {
    afterObserve.invoke()

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
      throw TimeoutException("LiveData value was never set.")
    }

  } finally {
    this.removeObserver(observer)
  }

  @Suppress("UNCHECKED_CAST")
  return data as T
}